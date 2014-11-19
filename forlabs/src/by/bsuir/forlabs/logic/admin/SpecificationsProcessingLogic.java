package by.bsuir.forlabs.logic.admin;

import by.bsuir.forlabs.connectionpool.ConnectionPool;
import by.bsuir.forlabs.connectionpool.WrapperConnector;
import by.bsuir.forlabs.dao.CarDao;
import by.bsuir.forlabs.dao.CategoryDao;
import by.bsuir.forlabs.dao.SpecificationDao;
import by.bsuir.forlabs.exceptions.DaoException;
import by.bsuir.forlabs.exceptions.LogicalException;
import by.bsuir.forlabs.subjects.Car;
import by.bsuir.forlabs.subjects.Specification;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SpecificationsProcessingLogic {

    /**
     *
     * @param request
     * @param specification
     * @param car
     * @throws IOException
     * @throws FileUploadException
     */
    public static void fillFromMultipartdata(HttpServletRequest request, Specification specification, Car car)
            throws IOException, FileUploadException {
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();

                if (fieldValue != null && !fieldValue.isEmpty()) {
                    switch (fieldName) {
                        case "producer":
                            specification.setProducer(fieldValue);
                            break;
                        case "model":
                            specification.setModel(fieldValue);
                            break;
                        case "year":
                            specification.setYear(Integer.parseInt(fieldValue));
                            break;
                        case "transmission":
                            specification.setTransmission(fieldValue);
                            break;
                        case "fuelType":
                            specification.setFuelType(fieldValue);
                            break;
                        case "engineCapacity":
                            specification.setEngineCapacity(Integer.parseInt(fieldValue));
                            break;
                        case "costPerDay":
                            specification.setCostPerDay(Float.parseFloat(fieldValue));
                            break;
                        case "idCategory":
                            specification.setIdCategory(Integer.parseInt(fieldValue));
                            break;
                        case "number":
                            car.setNumber(fieldValue);
                            break;
                    }
                }
            } else {
                String fieldName = item.getFieldName();
                String fileName = FilenameUtils.getName(item.getName());
                if ( fileName != null && !fileName.isEmpty()) {
                    specification.setImage(item);
                }
            }
        }

    }

    /**
     *
     * @param specification
     * @return
     */
    public static boolean requiredFieldsEmpty(Specification specification) {

        if (specification.getProducer() == null) {
            return true;
        }
        if (specification.getModel() == null) {
            return true;
        }
        if (specification.getYear() == 0) {
            return true;
        }
        if (specification.getTransmission() == null) {
            return true;
        }
        if (specification.getFuelType() == null) {
            return true;
        }
        if (specification.getEngineCapacity() == 0) {
            return true;
        }
        if (specification.getCostPerDay() == 0) {
            return true;
        }
        if (specification.getImage() == null) {
            return true;
        }

        return false;

    }



    /**
     *
     * @param specification
     * @param car
     * @throws LogicalException
     */

    public static void addSpecifications(Specification specification, Car car)
            throws LogicalException {

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnector wc = pool.getConnection();

        try {
            wc.setAutoCommit(false);
            CategoryDao categoryDao = new CategoryDao(wc);
            if (categoryDao.findEntityById(specification.getIdCategory()) != null) {
                SpecificationDao specificationDao = new SpecificationDao(wc);
                int idSpecification;
                if ((idSpecification = specificationDao.create(specification)) == 0) {
                    throw new LogicalException("Specifications wasn't added");
                }
                else {
                    car.setIdSpecification(idSpecification);
                    CarDao carDao = new CarDao(wc);
                    int idCar;
                    if ((idCar = carDao.create(car)) == 0) {
                        throw new LogicalException("Car wasn't added");
                    }
                    else{
                        wc.commit();
                    }
                }
            }
            else {
                throw new LogicalException("Category with id = " + specification.getIdCategory() + " doesn't exist");
            }

        } catch (DaoException | SQLException e) {
            throw new LogicalException(e);
        } finally {
            try {
                wc.setAutoCommit(false);
            } catch (SQLException e) {
                throw new LogicalException(e);
            }
            pool.releaseConnection(wc);
        }

    }

}

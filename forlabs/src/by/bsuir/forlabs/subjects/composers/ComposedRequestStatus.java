package by.bsuir.forlabs.subjects.composers;

import by.bsuir.forlabs.subjects.ClientRequest;
import by.bsuir.forlabs.subjects.Status;

public class ComposedRequestStatus  implements Composed{

    private ClientRequest clientRequest;
    private Status status;

    public ComposedRequestStatus() {

    }

    public ClientRequest getClientRequest() {
        return clientRequest;
    }

    public void setClientRequest(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

package GUI_Project;

public class Check extends Thread {

    private Service service;

    public Check(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < service.carServiceSpots.length; i++) {
                service.carServiceSpots[i].checkIfReadyToSwap();
                if (service.carServiceSpots[i].getStatus().equals("EMPTY")) {
                    service.carServiceSpots[i].setFromQueue();
                }
            }
            for (int i = 0; i < service.consumerWarehouses.length; i++) {
                service.consumerWarehouses[i].checkRoom();
                service.consumerWarehouses[i].checkIfItIsTimeToCleanUp();
            }
            for (int i = 0; i < service.parkingSpaces.length; i++) {
                service.parkingSpaces[i].checkRoom();
                service.parkingSpaces[i].checkIfItIsTimeToCleanUp();
            }
        }
    }


}


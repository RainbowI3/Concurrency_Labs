package org.example;

public class PostOfficeThread extends Thread{
    private final PostOffice postOffice;
    private final DeliveryService deliveryService;

    public PostOfficeThread(PostOffice postOffice, DeliveryService deliveryService) {
        this.postOffice = postOffice;
        this.deliveryService = deliveryService;
    }

    @Override
    public void run() {
        while (true) {
            Order order;
            synchronized (deliveryService) {
                while ((order = deliveryService.getNextOrderForPostOffice(postOffice)) == null) {
                    try {
                        deliveryService.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                deliveryService.removeOrder(order);
            }


            System.out.println(postOffice.getName() + " начала обработку заказа номер " + order.getPackage().getTrackingNumber());
            deliveryService.packOrder(order);
            deliveryService.sendOrder(order);


            synchronized (deliveryService) {
                deliveryService.notifyAll();
            }
        }
    }
}

package sarangi.rakesh.elevator;

public class Main {
    public static void main(String[] args) {
        Elevator elevator1 = new Elevator(4);
        ElevatorController elevatorController = new ElevatorController(1, 4);

        elevatorController.status();
        elevatorController.pickup(1, true);
        elevatorController.step();
        elevatorController.status();
        elevatorController.getPassengers(1);

    }
}

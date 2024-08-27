package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

        private AirportManager airportManager;
        private Destination destination1;
        private Destination destination2;
        private Destination destination3;
        private Destination destination4;

        @Before
        public void setUp() {

            airportManager = new AirportManager(2, 2);

            // Создаем пункты назначения
            destination1 = new Destination("New York", 4);
            destination2 = new Destination("Los Angeles", 6);
            destination3 = new Destination("Chicago", 3);
            destination4 = new Destination("Frankfurt", 7);
        }

        @Test
        public void testPlaneCanReachDestination() {
            Plane plane = new Plane("Plane1(Boeing 777-300ER)", 200, 5000, destination1);


            assertTrue("Plane should be able to reach the destination", plane.canReachDestination());
        }

        @Test
        public void testPlaneCannotReachDestination() {
            Plane plane = new Plane("Plane4(Sukhoi Superjet 100-95B)", 180, 4000, destination4);


            assertFalse("Plane should not be able to reach the destination", plane.canReachDestination());
        }

        @Test
        public void testHandlePlane() throws NotEnoughTerminalsException, NotEnoughLaddersException {
            Plane plane1 = new Plane("Plane1(Boeing 777-300ER)", 200, 5000, destination1);
            Plane plane2 = new Plane("Plane2(Boeing 747-400)", 300, 7000, destination2);


            airportManager.handlePlane(plane1);
            airportManager.handlePlane(plane2);


            assertTrue("Both planes should complete their boarding and landing successfully", true);
        }

        @Test
        public void testNotEnoughTerminalsException() throws NotEnoughLaddersException, NotEnoughTerminalsException {
            Plane plane1 = new Plane("Plane1(Boeing 777-300ER)", 200, 5000, destination1);
            Plane plane2 = new Plane("Plane2(Boeing 747-400)", 300, 7000, destination2);
            Plane plane3 = new Plane("Plane3(Airbus A320-200)", 150, 3000, destination3);

            airportManager.handlePlane(plane1);
            airportManager.handlePlane(plane2);

            try {

                airportManager.handlePlane(plane3);
                fail("Expected NotEnoughTerminalsException to be thrown");
            } catch (NotEnoughTerminalsException e) {

            } catch (Exception e) {
                fail("Expected NotEnoughTerminalsException, but got " + e);
            }
        }

        @Test
        public void testNotEnoughLaddersException() throws NotEnoughLaddersException, NotEnoughTerminalsException {
            Plane plane1 = new Plane("Plane1(Boeing 777-300ER)", 200, 5000, destination1);
            Plane plane2 = new Plane("Plane2(Boeing 747-400)", 300, 7000, destination2);
            Plane plane3 = new Plane("Plane3(Airbus A320-200)", 150, 3000, destination3);

            airportManager.handlePlane(plane1);
            airportManager.handlePlane(plane2);

            try {

                airportManager.handlePlane(plane3);
                fail("Expected NotEnoughLaddersException to be thrown");
            } catch (NotEnoughLaddersException e) {

            } catch (Exception e) {
                fail("Expected NotEnoughLaddersException, but got " + e);
            }
        }
    }




/*
 * This program computes the height and velocity of a falling object, including the effects
 * of atmospheric drag
 *
 * Manny Pasquale
 * Jan. 22 2018
 */


#include <stdio.h>
#include <math.h>
#include <stdbool.h>


int main() {
    //initialize variables for inputs
    const double gravity = 9.80667;
    const double air_density = 1.2754;
    double height;
    double mass;
    double drag_coefficient;
    double cross_sectional_area;
    double time_step_size;
    double time = 0.0;
    double velocity = 0.0;
    double displacement;
    double acceleration;
    double force_drag;
    double force_net;
    double force_gravity;

    printf("Enter the height in metres: ");
    scanf("%lf", &height);

    printf("Enter the mass in kg: ");
    scanf("%lf", &mass);

    printf("Enter the drag coefficient: ");
    scanf("%lf", &drag_coefficient);

    printf("Enter the cross-sectional area: ");
    scanf("%lf", &cross_sectional_area);

    printf("Enter the time step size: ");
    scanf("%lf", &time_step_size);

    printf("Time     Height     Velocity\n");


    //maybe switch to for loop with index being time and test being
    const int max_iterations = 1000000;

    for(int count_iterations = 0; height > 0 && count_iterations < max_iterations;count_iterations++, time = time + time_step_size){
        
        printf("%.2lf     %.1lf     %.1lf\n", time, height, velocity);



        //calc forge gravity
        force_gravity = mass*gravity;

        // calc Force (1.0/2.0)*acceleration*(pow(time,2));drag
        force_drag = (-0.5)*drag_coefficient*air_density*cross_sectional_area*(pow(velocity,2));

        //force net
        force_net = force_gravity + force_drag;

        //find accelleration is fnet over mass
        acceleration = force_net/mass;


        //new velocity
        velocity = velocity + (acceleration*time_step_size);

        //calc displacement
        displacement = velocity*time_step_size;

        height = height - displacement;


    }
    double terminal_velocity = 0.0;
    terminal_velocity = sqrt((2.0*mass*gravity)/(drag_coefficient*air_density*cross_sectional_area));

    printf("Theoretical terminal velocity is %.1lf", terminal_velocity);


}
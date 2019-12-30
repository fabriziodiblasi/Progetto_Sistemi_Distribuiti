#include <MQUnifiedsensor.h>

//Definitions
#define pin A0 //Analog input 0 of your arduino
#define type 5 //MQ5
//#define calibration_button 13 //Pin to calibrate your sensor

//Declare Sensor
MQUnifiedsensor MQ5(pin, type);

//Variables
float H2, LPG, CH4, CO, smoke;

void setup() {
  Serial.begin(9600); //Init serial port
   //init the sensor
  /*****************************  MQInicializar****************************************
  Input:  pin, type 
  Output:  
  Remarks: This function create the sensor object.
  ************************************************************************************/ 
  MQ5.inicializar(); 
  //pinMode(calibration_button, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:

}

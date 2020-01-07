#define DIGITAL_IN_SX 3
#define DIGITAL_IN_DX 2

#define ANALOG_IN_DX A0
#define ANALOG_IN_SX A3

//float sensor_dx=A0;
//float sensor_sx=A3;

float gas_value;
char buffer[8];

void scrivi_seriale(char id_sens, int value){
  buffer[0]='\0';
  sprintf(buffer,"%c%d%c",id_sens,value,'_');
  //Serial.print(buffer);
  Serial.write(buffer,sizeof(char)*8);
  
}

void allarm_dx(){
  float gas_value=analogRead(ANALOG_IN_DX);
  delay(500);
  scrivi_seriale('D',gas_value);
  

}

void allarm_sx(){
  float gas_value=analogRead(ANALOG_IN_SX);
  delay(500);
  scrivi_seriale('S',gas_value);
  
}

void setup()
{
  pinMode(ANALOG_IN_DX,INPUT);
  pinMode(ANALOG_IN_SX,INPUT);
  attachInterrupt(digitalPinToInterrupt(DIGITAL_IN_DX), allarm_dx, FALLING);
  attachInterrupt(digitalPinToInterrupt(DIGITAL_IN_SX), allarm_sx, FALLING);

  Serial.begin(9600);
}

void loop(){
  //gas_value=analogRead(ANALOG_IN_DX);
  if(digitalRead(DIGITAL_IN_DX) == HIGH) scrivi_seriale('D',0);

  delay(2000);

  //gas_value=analogRead(ANALOG_IN_SX);
  if(digitalRead(DIGITAL_IN_SX) == HIGH) scrivi_seriale('S',0);

  delay(2000);
}

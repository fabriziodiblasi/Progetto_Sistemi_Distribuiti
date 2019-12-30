float sensor_dx=A0;
float sensor_sx=A3;
float gas_value;
char buffer[8];

void scrivi_seriale(char id_sens, int value){
  sprintf(buffer,"%c%d%c",id_sens,value,'_');
  //Serial.print(buffer);
  Serial.write(buffer,sizeof(char)*8);
  buffer[0]='\0';
}

void setup()
{
  pinMode(sensor_dx,INPUT);
  pinMode(sensor_sx,INPUT);

  Serial.begin(9600);
}

void loop()
{
  gas_value=analogRead(sensor_dx);
  scrivi_seriale('D',gas_value);
  delay(2000);
  gas_value=analogRead(sensor_sx);
  scrivi_seriale('S',gas_value);
  delay(2000);
}

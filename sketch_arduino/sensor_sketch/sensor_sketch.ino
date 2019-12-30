float sensor_dx=A0;
float sensor_sx=A3;
float gas_value;
void setup()
{
  pinMode(sensor_dx,INPUT);
  pinMode(sensor_sx,INPUT);

  Serial.begin(9600);
}

void loop()
{
  gas_value=analogRead(sensor_dx);
  Serial.print("DX_");Serial.println(gas_value);
  delay(2000);
  gas_value=analogRead(sensor_sx);
  Serial.print("SX_");Serial.println(gas_value);
  delay(2000);
}

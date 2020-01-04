#include <Servo.h>

#define LED_ROSSO_SX 3
#define LED_VERDE_SX 2

#define LED_ROSSO_DX 4
#define LED_VERDE_DX 5

#define PIN_SERVO 9
#define PIN_SWITCH_SERVO 12

char chiudi_sx = 'A'; // chiude il servomotore ed il led sx
char chiudi_dx = 'B'; // chiude il servomotore ed il led dx

char chiudi_all = 'C'; // chiude il servomotore, il led dx & sx

char apri_sx = 'X'; // apre il servomotore ed il led sx
char apri_dx = 'Y'; // apre il servomotore ed il led dx


Servo myservo;
int pos, flag_servo, switch_servo;
volatile int stato = 0;

char incomingByte ="";

void apri_valvola() {

  if (flag_servo == 1 ) {
    flag_servo = 0;
    for (pos = pos; pos >= 0; pos--) {
      myservo.write(pos);
      delay(15);
    }
  }
  return;

}

void chiudi_valvola() {

  if (flag_servo == 0 ) {
    flag_servo = 1;
    for (pos = pos; pos < 90; pos++) {
      myservo.write(pos);
      delay(15);
    }
  }
  return;
}

//--------------- VERDE -----------------------------------

void accendiVerdeDx() {
  digitalWrite(LED_VERDE_DX, HIGH);
  return;
}

void accendiVerdeSx() {
  digitalWrite(LED_VERDE_SX, HIGH);
  return;
}

void spegniVerdeDx() {
  digitalWrite(LED_VERDE_DX, LOW);
  return;
}

void spegniVerdeSx() {
  digitalWrite(LED_VERDE_SX, LOW);
  return;
}

//--------------- ROSSO -----------------------------------

void accendiRossoDx() {
  digitalWrite(LED_ROSSO_DX, HIGH);
  return;
}

void accendiRossoSx() {
  digitalWrite(LED_ROSSO_SX, HIGH);
  return;
}

void spegniRossoDx() {
  digitalWrite(LED_ROSSO_DX, LOW);
  return;
}

void spegniRossoSx() {
  digitalWrite(LED_ROSSO_SX, LOW);
  return;
}

//------------------------------------------------------------

void setup() {
  pinMode(LED_ROSSO_SX, OUTPUT);
  pinMode(LED_ROSSO_DX, OUTPUT);
  pinMode(LED_VERDE_SX, OUTPUT);
  pinMode(LED_VERDE_DX, OUTPUT);
  Serial.begin(9600);
  myservo.attach(PIN_SERVO);
  myservo.write(0);
  pos = 0;
  flag_servo = 0;
  stato = 0;
  incomingByte="";
}

void loop() {
  if (stato == 0) {
    accendiVerdeDx();   // accende il LED VERDE A SX
    accendiVerdeSx();    // accende il LED VERDE A DX
    spegniRossoDx();   // accende il LED VERDE A SX
    spegniRossoSx();    // accende il LED VERDE A DX
    apri_valvola();
    stato = 1;
  }
  incomingByte = Serial.read();
  if (stato != 0 && incomingByte !=""){
    // put your main code here, to run repeatedly:
    
    //applico la decisione dello stato in base al carattere ricevuto dal bot
    if (incomingByte == chiudi_sx && stato != 2) {
      //chiudo il servomotore e spengo il led verde a sinistra ed accendo il rosso
      chiudi_valvola();
      spegniVerdeSx();
      accendiRossoSx();
      stato = 2;
    }
    if (incomingByte == chiudi_dx && stato != 3) {
      //chiudo il servomotore e spengo il led verde a sinistra ed accendo il rosso
      chiudi_valvola();
      spegniVerdeDx();
      accendiRossoDx();
      stato = 3;
    }
    if (incomingByte == apri_sx && stato != 4) {
      //chiudo il servomotore e spengo il led verde a sinistra ed accendo il rosso
      apri_valvola();
      spegniRossoSx();
      accendiVerdeSx();
      stato =4;
    }
  
    if (incomingByte == apri_dx && stato !=5) {
      //chiudo il servomotore e spengo il led verde a sinistra ed accendo il rosso
      apri_valvola();
      spegniRossoDx();
      accendiVerdeDx();
      stato=5;
    }
  }

}

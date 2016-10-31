#include <PinChangeInt.h>
#include <eHealth.h>

int cont = 0;

void setup() {
  Serial.begin(115200);  
  eHealth.initPulsioximeter();

  //Attach the inttruptions for using the pulsioximeter.   
  PCintPort::attachInterrupt(6, readPulsioximeter, RISING);
}

void loop() {

  while(Serial.read() != 'W');
  Serial.print("1"); 
  
  while(Serial.read() != 'W');
  Serial.print(eHealth.getBPM());

  while(Serial.read() != 'W');
  Serial.print("2");
  
  while(Serial.read() != 'W');
  Serial.print(eHealth.getOxygenSaturation());
  
  delay(2000);
}


//Include always this code when using the pulsioximeter sensor
//=========================================================================
void readPulsioximeter(){  

  cont ++;

  if (cont == 50) { //Get only of one 50 measures to reduce the latency
    eHealth.readPulsioximeter();  
    cont = 0;
  }
}



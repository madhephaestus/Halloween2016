/*
 * WiiChuckDemo -- 
 *
 * 2008 Tod E. Kurt, http://thingm.com/
 *
 */

#include <Wire.h>
#include "nunchuck_funcs.h"
#include <Servo.h>


int loop_cnt=0;

int accx,accy,accz,zbut,cbut,joyx,joyy;
int ledPin = 13;

Servo eyepan;
Servo eyetilt;
Servo jaw;
void setup()
{
    Serial.begin(9600);


    nunchuck_setpowerpins();
    nunchuck_init(); // send the initilization handshake
    eyepan.attach(D6);
    eyetilt.attach(D4);
    jaw.attach(D3);
    Serial.print("WiiChuckDemo ready\n");
}
// 130 50
int range= 20;
int max = 127+range;
int min =127-range;
void loop()
{
   
    if( loop_cnt > 20 ) { // every 20 msecs get new data
        loop_cnt = 0;
        nunchuck_get_data();

        accx  = nunchuck_accelx(); // ranges from approx 70 - 182
        accy  = nunchuck_accely(); // ranges from approx 65 - 173
        accz  = nunchuck_accelz(); // ranges from approx 65 - 173
        zbut = nunchuck_zbutton();
        cbut = nunchuck_cbutton(); 
        joyy = 255-nunchuck_joyx()+8;
        joyx = 255-nunchuck_joyy()-10;
        
        if(joyx>max)
          joyx=max;
        if(joyy>max)
          joyy=max;
          
        if(joyx<min)
          joyx=min;
        if(joyy<min)
          joyy=min;  
          
        eyetilt.write(joyx);
        eyepan.write(joyy);  
        jaw.write(zbut?60:20); 
        Serial.print("\r\nFrame \n\tx= "); Serial.print(joyx);
        Serial.print("\r\n\t y : "); Serial.print(joyy);
        //Serial.print("\r\n\taccy: "); Serial.print(zbut);
        //Serial.print("\tzbut: "); Serial.print((byte)zbut,DEC);
        //Serial.print("\tcbut: "); Serial.println((byte)cbut,DEC);
    }
    loop_cnt++;
    delay(1);
}

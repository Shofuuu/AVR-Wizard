package com.example.avrwizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GenerateCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
        );

        Button btnReadADC = (Button) findViewById(R.id.btnCodeADC);
        Button btnReadUART = (Button) findViewById(R.id.btnCodeReadUART);
        Button btnWriteUART = (Button) findViewById(R.id.btnCodeWriteUART);
        Button btnReadButton = (Button) findViewById(R.id.btnCodeReadButton);
        Button btnWriteOutput = (Button) findViewById(R.id.btnCodeOutput);
        Button btnWritePWM = (Button) findViewById(R.id.btnCodePWM);

        btnReadADC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codereadadc();
            }
        });
        btnReadUART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codereaduart();
            }
        });
        btnWriteUART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codewriteuart();
            }
        });
        btnReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codereadbutton();
            }
        });
        btnWriteOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codewriteoutput();
            }
        });
        btnWritePWM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codewritepwm();
            }
        });

        TextView codeInformation = (TextView) findViewById(R.id.codeInformation);
    }

    private void codereadadc(){
        Intent intent = new Intent(this, GeneratedProgram.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setFunctionCode("/*ADC Code*/\n" +
                "#include <avr/io.h>\n" +
                        "#include <util/delay.h>\n" +
                        "void ADC_init(){\n"+
                "	ADCSRA |= (1<<ADEN) | (1<<ADPS0) | (1<<ADPS1) | (1<<ADPS2);\n"+
                "	ADMUX |= (1<<REFS1) | (1<<REFS0);\n"+
                "}"+
                "int ADC_Read(){\n"+
                "	ADCSRA |= (1<<ADSC);\n"+
                "	while((ADCSRA & (1<<ADSC)));\n"+
                "	return ADCW;\n"+
                "}");
        json.setMainCode(
                "int main(){\n" +
                "	ADC_Init();\n" +
                "	ADC_Read();\n" +
                "}\n"
        );

        startActivity(intent);
    }

    private void codereaduart(){
        Intent intent = new Intent(this, GeneratedProgram.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setFunctionCode("/*UART READ*/\n" +
                "#include <avr/io.h>\n" +
                        "#include <util/delay.h>\n" +
                "void USART_init(int baudrate){\n" +
                "\tint ubrr = ((F_CPU)/(baudrate*16UL)-1);\n" +
                "\n" +
                "\tUBRRL = (unsigned char)(ubrr);\n" +
                "\tUBRRH = (ubrr>>8);\n" +
                "\tUCSRB |= (1<<RXEN) | (1<<TXEN);\n" +
                "\tUCSRC |= (1<<URSEL) | (1<<UCSZ1) | (1<<UCSZ0);\n" +
                "}\n" +
                "unsigned char USART_read(void){\n" +
                "\twhile(!(UCSRA & (1<<RXC)));\n" +
                "\treturn UDR;\n" +
                "}"
        );
        json.setMainCode(
            "int main(){\n" +
                    "\tUSART_init(9600);\n" +
                    "\tUSART_read();\n" +
                    "\n" +
                    "\twhile(1);\n" +
                    "}"
        );

        startActivity(intent);
    }

    private void codewriteuart(){
        Intent intent = new Intent(this, GeneratedProgram.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setFunctionCode("/*UART Write*/\n" +
                "#include <avr/io.h>\n" +
                        "#include <util/delay.h>\n" +
                "void USART_init(int baudrate){\n" +
                "\tint ubrr = ((F_CPU)/(baudrate*16UL)-1);\n" +
                "\n" +
                "\tUBRRL = (unsigned char)(ubrr);\n" +
                "\tUBRRH = (ubrr>>8);\n" +
                "\tUCSRB |= (1<<RXEN) | (1<<TXEN);\n" +
                "\tUCSRC |= (1<<URSEL) | (1<<UCSZ1) | (1<<UCSZ0);\n" +
                "}\n" +
                "void USART_write(char c){\n" +
                "\twhile(!(UCSRA & (1<<UDRE)));\n" +
                "\tUDR = c;\n" +
                "}\n" +
                "\n" +
                "void USART_writes(char* s){\n" +
                "\twhile(*s){\n" +
                "\t\tUSART_write(*s);\n" +
                "\t\ts++;\n" +
                "\t}\n" +
                "}"
            );
        json.setMainCode(
                "int main(){\n" +
                        "\tUSART_init(9600);\n" +
                        "\tUSART_writeln(\"Hello World!\");\n" +
                        "\n" +
                        "\twhile(1);\n" +
                        "}"
        );

        startActivity(intent);
    }

    private void codereadbutton(){
        Intent intent = new Intent(this, GeneratedProgram.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setFunctionCode("/*GPIO Output Write*/\n" +
                "#include <avr/io.h>\n" +
                "#include <util/delay.h>"
        );
        json.setMainCode(
                "int main(){\n" +
                        "\tDDRD = 0b10000000;\n" +
                        "\n" +
                        "\twhile(1){\n" +
                        "\t\tPORTD = 0b10000000;\n" +
                        "\t\t_delay_ms(500);\n" +
                        "\t\tPORTD = 0b00000000;\n" +
                        "\t\t_delay_ms(500);\n" +
                        "\t}\n" +
                        "}"
        );

        startActivity(intent);
    }

    private void codewriteoutput(){
        Intent intent = new Intent(this, GeneratedProgram.class);
        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setFunctionCode("/*GPIO Output Write*/\n" +
                "#include <avr/io.h>\n" +
                "#include <util/delay.h>"
        );
        json.setMainCode(
                "int main(){\n" +
                        "\tDDRD = 0b10000000;\n" +
                        "\n" +
                        "\twhile(1){\n" +
                        "\t\tPORTD = 0b10000000;\n" +
                        "\t\t_delay_ms(500);\n" +
                        "\t\tPORTD = 0b00000000;\n" +
                        "\t\t_delay_ms(500);\n" +
                        "\t}\n" +
                        "}"
        );

        startActivity(intent);
    }

    private void codewritepwm(){
        Intent intent = new Intent(this, GeneratedProgram.class);

        JSONEngine json = (JSONEngine) getApplicationContext();

        json.setFunctionCode("/*GPIO Output Write*/\n" +
                "#include <avr/io.h>\n" +
                "#include <util/delay.h>"
        );
        json.setMainCode(
                "int main(){\n" +
                        "\tDDRD = 0b10000000;\n" +
                        "\n" +
                        "\twhile(1){\n" +
                        "\t\tPORTD = 0b10000000;\n" +
                        "\t\t_delay_ms(500);\n" +
                        "\t\tPORTD = 0b00000000;\n" +
                        "\t\t_delay_ms(500);\n" +
                        "\t}\n" +
                        "}"
        );

        startActivity(intent);
    }
}
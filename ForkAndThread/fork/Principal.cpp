/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor....: Jonathas David Santos do Nascimento
Matrícula: 201912179
Inicio...: 16 de agosto de 2012
Alteracao: 18 de agosto de 2012
Nome.....: Principal.c
Funcao...: Herança em cadeia com comando fork em c++
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
#include <iostream>
#include <unistd.h>
using namespace std;

int main()
{
    pid_t processo;
    processo = fork();
    
    switch(processo){
        case -1:{
            cout << "Erro no processo pai" << endl;
            exit(1);
        }//case -1 pai
        case 0:{
            cout << "nasce o pai" << endl;
            sleep(22);//22 segundos passados
            cout << "O pai tem o primeiro filho aos 22 anos" << endl;
            processo = fork();
            switch(processo){
                case -1:{
                    cout << "erro no primeiro filho" << endl;
                    exit(1);
                }//case -1 primeiro filho
                case 0:{//22 S passados
                    cout << "nasce o primeiro filho" << endl;
                    sleep(16);//16 + 22 = 38 segundos passados
                    cout << "O pai é avô (primeiro filho) aos 38 anos" << endl;
                    processo = fork();
                    switch(processo){
                        case -1:{
                            cout << "erro no primeiro neto" << endl;
                            exit(1);
                        }//case -1 do primeiro neto
                        case 0:{
                            cout << "nace o primeiro neto" << endl;
                            sleep(30);//30 + 38 = 68 segundos passados
                            cout << "O pai é bisavô (primeiro filho) aos 68 anos" << endl;
                            processo = fork();
                            switch(processo){
                                case -1:{
                                    cout << "Erro no bisneto" << endl;
                                    exit(1);
                                }//case -1 do bisneto
                                case 0:{
                                    cout << "nasce o bisneto" << endl;
                                    break;
                                }//case 0 do bisneto
                                default:{
                                    sleep(12);
                                    cout << "o bisneto morre aos 12 anos" << endl;
                                }//default do bisneto
                            }//switch do bisneto
                            break;
                        }//case 0 do primeiro neto
                        default:{
                            sleep(35);
                            cout << "morre o primeiro neto aos 35 anos" << endl;
                        }//default do primeiro neto
                    }//switch do primeiro neto
                    break;
                }//case 0 do primeiro filho
                default:{
                    sleep(3);//3 + 22 = 25 segundos passados
                    cout << "O pai tem o segundo filho aos 25 anos " << endl;
                    processo = fork();
                    switch(processo){
                        case -1:{
                            cout << "Erro no segundo filho" << endl;
                        }//case -1 do segundo filho
                        case 0:{
                            cout << "nasce o segundo filho" << endl;
                            sleep(20);//20 + 25 = 45 segundos passados
                            cout << "O pai é avô (segundo filho) aos 45 anos" << endl;
                            processo = fork();
                            switch(processo){
                                case -1:{
                                    cout << "Erro no segundo neto" << endl;
                                    exit(1);
                                }//case -1 do segundo neto
                                case 0:{
                                    cout << "nasce o segundo neto" << endl;
                                    break;
                                }//case 0 do segundo neto
                                default:{
                                    sleep(33);
                                    cout << "o segundo neto morre aos 33 anos" <<endl;
                                }//default do segundo neto
                            }//swtich do segundo neto
                            break;
                        }//case 0 do segundo filho
                        default:{
                            sleep(7);//7 + 25 = 32 segundos passados
                            cout << "O pai tem o terceiro filho aos 32 anos" << endl;
                            processo = fork();
                            switch(processo){
                                case -1:{
                                    cout << "erro no primeiro filho" << endl;
                                    exit(1);
                                }//case -1 do terceiro filho
                                case 0:{
                                    cout << "nasce o terceiro filho" << endl;
                                    break;
                                }//case 0 do terceiro filho
                                default:{
                                    sleep(55);//55 segundos passados
                                    cout << "O terceiro filho morre aos 55 anos" << endl;
                                }//default do terceiro filho
                            }//switch do terceiro filho
                            sleep(55);
                            cout << "segundo filho morre aos 55 anos" << endl;

                        }//default do segundo filho
                    }//processo do segundo filho
                    sleep(58);//58 + 3 = 61 segundos passados
                    cout << "morre o primeiro filho aos 61 anos" << endl;
                }//default do primeiro filho
            }//switch primeiro filho
            break;
        }//case 0 pai
        default:{
            sleep(90);
            cout << "morre o pai aos 90 anos" << endl;
        }//default do pai
    }//switch pai
    return 0;
}//fim do main

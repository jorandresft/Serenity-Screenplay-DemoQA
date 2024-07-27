#author: Jorge Franco
#language: es

Característica: realizar el registro de un estudiante en el sitio web DemoQA

  Yo como estudiante
  Quiero realizar el registro en el sitio web DemoQA
  Para comprabar que el registro sea exitoso

  Antecedentes:
    Dado "estudiante" abre el sitio web DemoQA

  @successful
  Esquema del escenario: : validar el registro de un estudiante en el sitio web DemoQA
    Y selecciona el elemento Form y la sub funcion Practice Form
    Cuando se ingresa la informacion en el formulario
      | firstName   | lastName   | email   | gender   | mobil   | state   | city   |
      | <firstName> | <lastName> | <email> | <gender> | <mobil> | <state> | <city> |
    Entonces se valida el mensaje "Thanks for submitting the form"

    Ejemplos:
      | firstName | lastName | email           | gender | mobil      | state   | city    |
      | jorge     | franco   | jorge@gmail.com | Male   | 5555555555 | Haryana | Panipat |
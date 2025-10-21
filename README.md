# Parcial AREP tercio 2

Para este parcial se nos pidió desarrollar un servidor proxy que mediante el algoritmo de round-robin delegara peticiones a 2 servicios diferentes, en este caso MathServices1 y MathServices2.

# Arquitectura

# Como ejecutar
Para ejecutar se deben tener 3 instancias EC2 en total, 2 de estas serán los math services que serán idénticos, es decir que muestran los mismos servicios. La otra instancia deberá ser el proxy, el cual redirige las peticiones mediante round robin

# Pruebas
- Código para redirección mediante el proxy usando el algoritmo round-robin a las 2 instancias EC2
Para esto se usó una variable atómica que actúa como comtador, se uso atómica por si se hacen peticiones a la vez, y la variable por cada petición aumenta en 1. Si la variable es par, entra al servidor 1, de lo contrario, entra al servidor 2
![alt text](images/image-2.png)
- Pruebas de que entra a los 2 servidores después de hacer varias peticiones
![alt text](images/image-3.png)
- Código para desarrollar el servicio de secuencia de collatz
![alt text](images/image-1.png)
- Prueba de ejecución correcta del ejercicio de collatz
![alt text](images/image.png)
- Ejecución en las instancias
![alt text](images/image-4.png)
![alt text](images/image-5.png)
![alt text](images/image-6.png)
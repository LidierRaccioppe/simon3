# SimonMeDijo

Diagrama Para Nada Molesto De Estado

---
Titulo: Maquina de Estados Del Simon Dice
---
````mermaid
stateDiagram-v2
Start/Restart --> SecuenciaColores
SecuenciaColores --> Start/Restart
SecuenciaColores --> Input
Input --> Start/Restart
Input --> Comprobar
Comprobar --> Start/Restart
Comprobar --> Perder
Comprobar --> SecuenciaColores
Perder --> Start/Restart
````

---
Titulo: Diagrama de Flujo Del Simon Dice Sin Codigo Explicito
---
#### Leyenda
- Cuadrado = Boton
- Rombo = If
- CuadradoConEsquinasCirculares = Proceso
````mermaid
flowchart TD
    id1([Inicio]) --> Start
    Start --> Restart
    Start --> aleatorioColor([aleatorioColor Rojo,Verde,Azul,Amarrillo])
    aleatorioColor --> BotonRojo
    aleatorioColor --> BotonVerde
    aleatorioColor --> BotonAzul
    aleatorioColor --> BotonAmarillo
    BotonRojo --> Enviar
    BotonVerde --> Enviar
    BotonAzul --> Enviar
    BotonAmarillo --> Enviar
    Enviar --> Comprobar{if colorPressed==colorRonda}
    Comprobar -->|True| aleatorioColor
    Comprobar -->|False| Restart
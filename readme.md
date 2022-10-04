<h1 align="center"> Projeto Sobre Agentes   </h1>

<h2 align="center"> Tema: 4. Implementar um modelo de aprendizado  </h2> 
 
<h2 align="center"> Introdução  </h2>

 Para a implementação de um modelo de aprendizado de Agentes, que impede o agente de retornar para os espaços limpos dentro de um período de três segundos, foi necessário a utilização de HashMap e de programação paralela.  

<h2 align="center"> Modelo de Aprendizado  </h2>					 

 Ao iniciar o programa o Labirinto e o Agente são criados, e o Agente é colocado em uma posição relativa ao tamanho do labirinto e nunca irá parar de se movimentar. O Agente limpa os espaços sujos e armazena em sua memória os locais limpos utilizando o HashMap, que armazena itens por índices, durante três segundos.  

 Ao tomar a decisão do próximo movimento, o agente verificar o local, caso esteja limpo e ainda não passou os três segundos de espera, ele irá para outra direção. Está tomada de decisão faz o uso do HashMap, que verifica se o próximo movimento ainda permanece armazenado. 

 Após o armazenamento do local que foi limpo, é iniciada uma thread para fazer a remoção da posição armazenada. Está thread recebe como nome a posição limpa e inicializa uma nova função que aguardar os três segundo e remove a posição do HashMap. 

<div align="center">
 <img src="https://user-images.githubusercontent.com/63758393/193724980-2350fe2a-c484-458c-9ccc-0826c74314b1.png" widht="586px">
</div>
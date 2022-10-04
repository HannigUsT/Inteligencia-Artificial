<h1 align="center"> Projeto Sobre Agentes   </h1>

<h2 align="center"> Tema: 4. Implementar um modelo de aprendizado  </h2> 
 
<h2 align="center"> Introdução  </h2>

 Para a implementação de um modelo de aprendizado de Agentes, que impede o agente de retornar para os espaços limpos dentro de um período de três segundos, foi necessário a utilização de HashMap e de programação paralela.  

<h2 align="center"> Modelo de Aprendizado  </h2>					 

 Ao iniciar o programa o Labirinto e o Agente são criados, e o Agente é colocado em uma posição relativa ao tamanho do labirinto e nunca irá parar de se movimentar. O Agente limpa os espaços sujos e armazena em sua memória os locais limpos utilizando o HashMap, que armazena itens por índices, durante três segundos.  

 Ao tomar a decisão do próximo movimento, o agente verificar o local, caso esteja limpo e ainda não passou os três segundos de espera, ele irá para outra direção. Está tomada de decisão faz o uso do HashMap, que verifica se o próximo movimento ainda permanece armazenado. 

 Após o armazenamento do local que foi limpo, é iniciada uma thread para fazer a remoção da posição armazenada. Está thread recebe como nome a posição limpa e inicializa uma nova função que aguardar os três segundo e remove a posição do HashMap. 

 <img widht="470" src="/img.png">

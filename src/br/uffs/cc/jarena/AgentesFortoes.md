# Relatório da Estratégia do Agente AgentesFortoes

## Integrantes
- Veronica Antonini Martini (Turma do professor Andrei)
- Joao Francisco Arcari (Turma do professor Geomar)

## Ideia principal
O agente tenta sobreviver o máximo possível, coletando energia dos cogumelos e fugindo de combates desfavoráveis. Agentes da mesma equipe trocam mensagens para avisar onde há cogumelos, fugindo de conflitos perigosos e fazendo divisões para maximizar a energia.

## Movimento
- No início, metade dos agentes vai para a direita e metade para a esquerda.
- Normalmente, anda sempre em linha reta na mesma direção.
- Só muda de direção quando:
  - bate em uma parede;
  - recebe a localização de um cogumelo (vai na direção dele);
  - precisa fugir (vai para o lado oposto ao que estava andando).
- Quando chega perto de um cogumelo (distância ≤ 1), para e começa a coletar energia.
- Se fica 3 turnos sem ganhar energia (cogumelo acabou ou saiu do lugar), escolhe uma nova direção aleatória e volta a andar em linha reta buscando novos cogumelos.

## Combate
- Ao sofrer dano, compara sua energia com a do inimigo.
- Se sua energia for menor, ativa o modo fuga.
- Fuga: anda na direção contrária à que estava seguindo.
- Se a energia for maior ou igual, não foge (continua o combate).

## Divisão (reprodução)
- Cada agente divide no máximo uma vez.
- Divide apenas se a energia for maior que 700 e for possível dividir, cenários favoraveis aonde a energia restante permite boa movimentação.

## Comunicação entre aliados
- Quando um agente recebe energia de um cogumelo, ele envia uma mensagem com sua posição (coordenadas x e y) para os aliados próximos.
- Ao receber uma mensagem, o agente guarda aquela posição como um cogumelo conhecido.
- Sempre que há cogumelos conhecidos, o agente se move em direção ao mais próximo, buscando coletar energia.

## Resumo dos métodos importantes
- pensa(): define a direção de movimento a cada turno.
- recebeuEnergia(): ativa o modo coleta e envia a posição do cogumelo aos agentes.
- tomouDano(): decide se deve fugir ou manter um combate de resultado favoravel.
- recebeuMensagem(): armazena a posição de cogumelos enviada por aliados facilitando a localização deles.
- ganhouCombate(): apenas envia uma mensagem de vitória avisando se a estratégia do agente foi a vencedora.

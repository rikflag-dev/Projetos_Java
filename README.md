# Projetos Java
Projetos feitos usando a Linguagem de Programação Java para fins de Estudos e Treino.

---

## Simples

### Gerador de Histórias
projeto Java com interface gráfica que gera histórias curtas e aleatórias com base em gêneros literários. O usuário pode escolher entre **Comédia**, **Terror** ou **Fantasia**, e o programa monta automaticamente uma história com início, meio e fim, exibindo-a em uma área de texto.

### Calendário da Produtividade

Um aplicativo de produtividade simples desenvolvido em **Java com Swing**, que permite aos usuários marcar tarefas em um calendário mensal com **cores por tipo de atividade** (estudo, trabalho, lazer) e visualizar as tarefas de cada dia.

### RPG de Texto

Um jogo simples de RPG em modo texto feito em **Java**, utilizando **Swing** como biblioteca gráfica.
O jogador pode explorar uma floresta, enfrentar inimigos, descansar para recuperar vida e mana, e tomar decisões que influenciam o desfecho.

### Tocador de Música Minimalista com Playlists

Um aplicativo desktop simples feito em Java utilizando Swing, que permite reproduzir arquivos `.mp3` com suporte a playlists. O player utiliza a biblioteca JLayer para decodificar e tocar as músicas em formato MP3.
Para rodar corretamente é necessário entrar na pasta do projeto e rodar no terminal esses dois comandos:
**javac -cp ".;jl1.0.1.jar" MusicPlayer.java** | 
**java -cp ".;jl1.0.1.jar" MusicPlayer**

### Cofre Virtual com Senha

**Cofre Virtual**, desenvolvido em **Java com Swing**, onde o usuário pode armazenar frases ou senhas pessoais protegidas por uma senha mestra.
O conteúdo salvo é criptografado em **Base64** e armazenado localmente em um arquivo (`cofre.txt`).

### Jogo da Memória

Um jogo da memória simples desenvolvido em Java utilizando a biblioteca Swing para interface gráfica. O jogo consiste em encontrar pares de cartas iguais em um tabuleiro 4x4. O tempo é cronometrado e, ao final, o jogador pode salvar seu tempo no ranking local em um arquivo (`ranking.txt`).

### Gerador de Paletas de Cores Aleatórias

Este projeto é uma aplicação desktop em Java utilizando Swing que gera paletas de cores aleatórias visualmente agradáveis. A interface exibe cinco cores diferentes, mostrando seus respectivos códigos hexadecimais. O usuário pode clicar nos códigos para copiá-los facilmente para a área de transferência.

---

## Intermediários

### Sistema de Relatórios Financeiros

Este projeto é uma aplicação Java Swing para visualização de relatórios financeiros mensais, focado no acompanhamento e análise de despesas por categoria. A interface gráfica exibe um gráfico de pizza (pie chart) interativo que representa a distribuição dos gastos, além de alertas para controle financeiro, como aviso quando os gastos ultrapassam 70% das receitas.

### Jogo de Importor

Este é um jogo simples desenvolvido em Java utilizando Swing, onde o jogador precisa identificar o impostor entre vários bots (NPCs) em diferentes salas. O jogo simula um ambiente de "sala" semelhante ao estilo Among Us, com bots se movendo aleatoriamente entre salas.
Após um tempo de exploração, inicia-se a fase de votação, onde o jogador pode votar em qual bot acredita ser o impostor. Se o jogador acertar, vence; caso contrário, perde. O jogo termina após a votação.

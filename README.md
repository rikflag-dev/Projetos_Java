# Projetos_Java
Projetos feitos usando a Linguagem de Programação Java para fins de Estudos e Treino.

## Gerador de Histórias
projeto Java com interface gráfica que gera histórias curtas e aleatórias com base em gêneros literários. O usuário pode escolher entre **Comédia**, **Terror** ou **Fantasia**, e o programa monta automaticamente uma história com início, meio e fim, exibindo-a em uma área de texto.

### 📋 Funcionalidades

- Interface gráfica amigável com `Swing`.
- Escolha de gênero da história via menu suspenso (`JComboBox`).
- Geração aleatória de histórias com base no gênero selecionado.
- Área de exibição com barra de rolagem para visualizar a história completa.

### 🧰 Bibliotecas Utilizadas

Este projeto utiliza **apenas bibliotecas padrão do Java**, principalmente do pacote `javax.swing` para a interface gráfica:

| Biblioteca        | Descrição |
|-------------------|-----------|
| `javax.swing.JFrame`      | Cria a janela principal do aplicativo. |
| `javax.swing.JLabel`      | Exibe texto estático (ex: "Gênero:"). |
| `javax.swing.JComboBox`   | Menu suspenso para seleção do gênero. |
| `javax.swing.JButton`     | Botão para acionar a geração da história. |
| `javax.swing.JTextArea`   | Área onde a história gerada é exibida. |
| `javax.swing.JPanel`      | Painel para agrupar componentes da interface. |
| `javax.swing.JScrollPane` | Adiciona barra de rolagem à área de texto. |
| `java.awt.BorderLayout`   | Gerenciador de layout para organizar os componentes na janela. |
| `java.util.Random`        | Utilizado para escolher aleatoriamente trechos da história. |


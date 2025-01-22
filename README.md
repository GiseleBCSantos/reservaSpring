> Site para controle de Reservas de Equipamentos e Espaços feito por Funcionários

<div style="display: flex; justify-content: space-evenly">
    <img src="https://github.com/user-attachments/assets/f52aca0c-a42e-4023-9018-2acdce161679" alt="Spring Reservas" width="300">
    <img src="https://github.com/user-attachments/assets/47b74051-6018-49b0-afa1-299ba0e57620" alt="Página de cadastro" width="300">
</div>

## 🚀 Instalando reservaSpring

Para clonar o reservaSpring, siga estas etapas:

Linux e macOS:

```
git clone https://github.com/GiseleBCSantos/reservaSpring
```

## ☕ Usando reservaSpring

Para usar reservaSpring, siga estas etapas:

1. Baixar dependencias pelo Maven
2. Alterar dados em src/resources/application.properties para o banco de dados utilizado na execução
3. Executar o arquivo src/main/java/br/com/ifpi/catce/reservaspring/ReservaSpringApplication

## Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas para as seguintes tarefas:

- [x] Adicionar entidade, repository, service e controller com Create e Read de Equipamento, Espaço, Funcionários e Reservas
- [x] Adicionar template LayoutPadrao para ser a base do site
- [x] Adicionar fragment de Header
- [x] Adicionar templates de listagem e criação de Equipamento, Espaço, Funcionários e Reservas
- [x] Vincular controllers a templates, vinculando seus atributos e com mensagens de validação
- [x] Adicionar Exceptions para nomes, emails e descrições já cadastradas
- [x] Adicionar paginação, filtragem e ordenação nas buscas
- [x] Melhorar o visual 
- [ ] Finalizar CRUD
- [ ] Adicionar autenticação

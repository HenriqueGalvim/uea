# Sistema de Gerenciamento de Atividades Acadêmicas — Spring Boot

Este projeto consiste em uma aplicação Spring Boot que oferece uma API RESTful para o gerenciamento de cursos, atividades, categorias e evidências (fotos), com autenticação JWT e controle de acesso por roles.

## ✅ Pré-requisitos

Certifique-se de ter os seguintes recursos instalados:

- Java 17+
- Maven
- IDE (IntelliJ, VS Code ou Eclipse)
- Ferramenta de teste de API: Swagger, Thunder Client ou Insomnia

---

## 🚀 Como executar a aplicação

### Passo 1: Clone o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### Passo 2: Execute com Maven

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada na porta `8080`.

---

## 🔐 Login e Autenticação

A autenticação é feita via JWT. Três usuários estão pré-cadastrados via `data.sql`:

| Email                      | Senha    | Role         |
|---------------------------|----------|--------------|
| admin@uea.edu.br          | 123456   | ROLE_ADMIN   |
| gerente@uea.edu.br        | 123456   | ROLE_GERENTE |
| secretaria@uea.edu.br     | 123456   | ROLE_SECRETARIO |

> As senhas estão criptografadas com BCrypt. Use `/api/auth/login` para obter um token.

---

## 📚 Documentação da API

Acesse o Swagger UI em:

```
http://localhost:8080/swagger-ui.html
```

No Swagger, é possível autenticar com JWT e testar todos os endpoints.

---

## 🔁 Endpoints disponíveis

### 🔐 Autenticação
- `POST /api/auth/login` — Retorna um JWT

### 📘 Cursos
- `GET /api/cursos` — Listar cursos
- `POST /api/cursos` — Criar curso (ADMIN)
- `PUT /api/cursos/{id}` — Atualizar curso
- `DELETE /api/cursos/{id}` — Remover curso

### 🏷️ Categorias
- `GET /api/categorias/public` — Listar categorias (acesso público)
- `POST /api/categorias` — Criar categoria (ADMIN)

### 📝 Atividades
- `GET /api/atividades/curso/{id}` — Listar atividades por curso
- `GET /api/atividades/publicadas` — Listar atividades públicas
- `POST /api/atividades` — Criar atividade (GERENTE)
- `PUT /api/atividades/{id}` — Editar atividade (GERENTE ou SECRETARIO)
- `PATCH /api/atividades/{id}/publicar` — Alterar status de publicação (GERENTE ou ADMIN)

### 🖼️ Fotos
- `POST /api/fotos/atividade/{id}` — Adicionar foto a uma atividade
- `GET /api/fotos/atividade/{id}` — Listar fotos por atividade

---

## 🧪 Exemplo de requisições

### Login (JWT)
```json
POST /api/auth/login
{
  "username": "admin@uea.edu.br",
  "password": "123456"
}
```

### Criar Curso
```json
POST /api/cursos
{
  "nome": "Licenciatura em Computação",
  "descricao": "Curso de formação de professores com foco em computação.",
  "idGerente": 2
}
```

### Criar Categoria
```json
POST /api/categorias
{
  "nome": "Extensão"
}
```

### Criar Atividade
```json
POST /api/atividades
{
  "titulo": "Semana de Programação",
  "descricao": "Palestras e oficinas sobre desenvolvimento.",
  "publicoAlvo": "Estudantes",
  "categoriaId": 1,
  "cursoId": 100,
  "data": "2025-05-10"
}
```

### Adicionar Foto
```json
POST /api/fotos/atividade/200
{
  "url": "https://exemplo.com/foto.jpg",
  "legenda": "Foto da palestra"
}
```

---

## 🧩 Controle de Acesso (Roles)

| Role           | Permissões principais                                    |
|----------------|----------------------------------------------------------|
| `ROLE_ADMIN`   | Total acesso aos cursos, categorias e atividades         |
| `ROLE_GERENTE` | Gerencia atividades do seu curso                         |
| `ROLE_SECRETARIO` | Adiciona evidências e ajusta dados                    |
| `VISITANTE`    | Acessa apenas rotas públicas                             |

---

## 🛠️ Erros comuns

- **"Table not found"**: confirme se `spring.jpa.hibernate.ddl-auto=update` está habilitado
- **"Access Denied"**: verifique se o JWT foi inserido corretamente no Swagger
- **"Foreign Key violation"**: verifique se a categoria ou curso existe

---

## 📌 Informações técnicas

- Banco: H2 (em memória)
- Documentação: Springdoc OpenAPI
- Autenticação: JWT
- Criptografia de senha: BCrypt
- Validações com `@Valid` e Bean Validation

---

## 👤 Autor

- Henrique Galvim  
- Projeto desenvolvido para disciplina de Desenvolvimento Web

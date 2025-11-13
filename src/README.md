# TaskFlow: Aplicativo de Lista de Tarefas Inteligente

Este é um aplicativo de lista de tarefas (To-Do List) construído com Next.js, React, TypeScript, Firebase e ShadCN para os componentes de UI. O aplicativo permite que os usuários gerenciem suas tarefas diárias, organizadas por categorias, com autenticação via Google.

## Como Rodar Localmente

Siga estas instruções para configurar e rodar o projeto em seu ambiente de desenvolvimento local.

### Pré-requisitos

- [Node.js](https://nodejs.org/) (versão 18 ou superior)
- [npm](https://www.npmjs.com/) (geralmente vem com o Node.js)
- [Git](https://git-scm.com/)

### Passos

1.  **Clone o repositório:**
    Abra seu terminal e clone o projeto em uma pasta de sua escolha.

    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```
    *Substitua `https://github.com/seu-usuario/seu-repositorio.git` pela URL real do seu repositório Git.*

2.  **Instale as dependências:**
    Dentro da pasta do projeto, execute o seguinte comando para instalar todas as dependências listadas no `package.json`.

    ```bash
    npm install
    ```
3.  **Configure o Firebase:**
    Para que o aplicativo se conecte ao Firebase, você precisa criar um projeto e adicionar as chaves de configuração.

    **a. Crie e Configure seu Projeto no Firebase:**
    - Acesse o [Console do Firebase](https://console.firebase.google.com/) e crie um novo projeto.
    - No seu projeto, vá para **Authentication**, clique em **Começar** e ative o provedor de login do **Google**.
    - Em seguida, vá para **Firestore Database**, clique em **Criar banco de dados**, inicie em **modo de produção** e escolha um local para o seu servidor.

    **b. Obtenha as Chaves de Configuração:**
    - Na página principal do seu projeto, clique no ícone da Web (`</>`) para adicionar um novo aplicativo da Web.
    - Dê um nome ao seu aplicativo e registre-o. O Firebase fornecerá um objeto de configuração.

    **c. Adicione as Chaves ao Projeto:**
    - No arquivo `src/firebase/config.ts`, substitua o objeto `firebaseConfig` existente pelo objeto que você copiou do Firebase.

    **d. (MUITO IMPORTANTE) Autorize os Domínios de Acesso:**
    - Para que o login com Google funcione (tanto localmente quanto em produção), você precisa autorizar os domínios que acessarão a autenticação.
    - No Console do Firebase, vá para **Authentication** > **Settings**.
    - Em **Domínios autorizados**, adicione os seguintes domínios:
        - `localhost` (para desenvolvimento local)
        - O domínio onde seu site está hospedado (ex: `todoperseu.netlify.app`)

4.  **Rode o servidor de desenvolvimento:**
    Execute o comando abaixo para iniciar o aplicativo em modo de desenvolvimento.

    ```bash
    npm run dev
    ```

5.  **Acesse o aplicativo:**
    Abra seu navegador e acesse [http://localhost:9002](http://localhost:9002) (ou a porta que for indicada no seu terminal).


## Estrutura do Projeto

```
.
├── .env
├── README.md
├── apphosting.yaml
├── components.json
├── docs
│   └── backend.json
├── firestore.rules
├── next.config.ts
├── package.json
├── src
│   ├── ai
│   │   ├── dev.ts
│   │   └── genkit.ts
│   ├── app
│   │   ├── actions.ts
│   │   ├── globals.css
│   │   ├── layout.tsx
│   │   └── page.tsx
│   ├── components
│   │   ├── FirebaseErrorListener.tsx
│   │   ├── add-task-form.tsx
│   │   ├── edit-task-dialog.tsx
│   │   ├── icons.tsx
│   │   ├── task-card.tsx
│   │   ├── task-list.tsx
│   │   ├── ui
│   │   │   ├── accordion.tsx
│   │   │   ├── alert-dialog.tsx
│   │   │   ├── alert.tsx
│   │   │   ├── avatar.tsx
│   │   │   ├── badge.tsx
│   │   │   ├── button.tsx
│   │   │   ├── calendar.tsx
│   │   │   ├── card.tsx
│   │   │   ├── carousel.tsx
│   │   │   ├── chart.tsx
│   │   │   ├── checkbox.tsx
│   │   │   ├── collapsible.tsx
│   │   │   ├── dialog.tsx
│   │   │   ├── dropdown-menu.tsx
│   │   │   ├── form.tsx
│   │   │   ├── input.tsx
│   │   │   ├── label.tsx
│   │   │   ├── menubar.tsx
│   │   │   ├── popover.tsx
│   │   │   ├── progress.tsx
│   │   │   ├── radio-group.tsx
│   │   │   ├── scroll-area.tsx
│   │   │   ├── select.tsx
│   │   │   ├── separator.tsx
│   │   │   ├── sheet.tsx
│   │   │   ├── sidebar.tsx
│   │   │   ├── skeleton.tsx
│   │   │   ├── slider.tsx
│   │   │   ├── switch.tsx
│   │   │   ├── table.tsx
│   │   │   ├── tabs.tsx
│   │   │   ├── textarea.tsx
│   │   │   ├── toast.tsx
│   │   │   └── toaster.tsx
│   │   └── user-auth.tsx
│   ├── firebase
│   │   ├── auth
│   │   │   └── handle-redirect.ts
│   │   ├── client-provider.tsx
│   │   ├── config.ts
│   │   ├── error-emitter.ts
│   │   ├── errors.ts
│   │   ├── firestore
│   │   │   ├── use-collection.tsx
│   │   │   └── use-doc.tsx
│   │   ├── index.ts
│   │   └── provider.tsx
│   ├── hooks
│   │   ├── use-mobile.tsx
│   │   └── use-toast.ts
│   ├── lib
│   │   ├── placeholder-images.json
│   │   ├── placeholder-images.ts
│   │   └── utils.ts
│   └── types
│       └── index.ts
├── tailwind.config.ts
└── tsconfig.json
```
# TaskFlow: Aplicativo de Lista de Tarefas Inteligente

Este é um aplicativo de lista de tarefas (To-Do List) construído com Next.js, React, TypeScript, Firebase e ShadCN para os componentes de UI. O aplicativo permite que os usuários gerenciem suas tarefas diárias, organizadas por categorias, com autenticação via Google.

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

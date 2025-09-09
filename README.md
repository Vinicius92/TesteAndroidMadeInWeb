![Kotlin](https://img.shields.io/badge/Kotlin-2.0-blue?logo=kotlin)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-stable-blue?logo=jetpackcompose)
![Hilt](https://img.shields.io/badge/Hilt-DI-orange?logo=dagger)
![Koin](https://img.shields.io/badge/Koin-DI-purple)
![Material3](https://img.shields.io/badge/Material-3-blue?logo=materialdesign)
![Android](https://img.shields.io/badge/Android-7.0%2B-green?logo=android)
![License](https://img.shields.io/github/license/Vinicius92/TesteAndroidMadeInWeb)


# 📱 App Android Teste da MadeInWeb

Este é um aplicativo Android desenvolvido em **Kotlin**, utilizando **MVVM + Hilt** para a tela em XML e **MVI + Koin** para a tela em Compose.  
O projeto foi criado como teste pratico para a MadeInWeb, de boas práticas de arquitetura, UI em XML/Compose e integração com ViewModels.

---

## ✨ Funcionalidades

- Tela de **login em XML (MVVM + Hilt)**  
  - Validação de e-mail e senha  
  - Regras de senha (mínimo de caracteres, caractere especial, maiúscula, número)  
  - Loading bloqueando a UI durante requisições  
  - Mensagens de erro exibidas dinamicamente  

- Tela de **login em Compose (MVI + Koin)**  
  - Mesmo fluxo da versão XML, mas com Jetpack Compose  
  - Preview para design no Android Studio  
  - entre outros

---

## 🛠️ Tecnologias & Bibliotecas

- **Kotlin** como linguagem principal
- **MVVM + Hilt (Dagger)** para injeção de dependência no fluxo XML
- **MVI + Koin** para injeção de dependência no fluxo Compose
- **Jetpack Compose** para UI declarativa
- **Material3** para UI components
- **ViewBinding** para XML
- **ConstraintLayout** para layouts responsivos no XML
- **LiveData / StateFlow** para gerenciamento de estado
- **Coroutines** para operações assíncronas

## 📂 Estrutura do Projeto

```text
app/
 ├── ui/
 │    ├── xml/         # Telas em XML (MVVM + Hilt)
 │    └── compose/     # Telas em Compose (MVI + Koin)
 ├── domain/           # UseCases e Regras de negócio
 ├── infra/            # Repositórios e fontes de dados
 └── di/               # Configurações de injeção de dependência (Hilt/Koin)
```

## 🔑 Credenciais de Teste

Para simular o fluxo de sucesso (exibir Toast de sucesso ao clicar em "Entrar") 
utilize:

- **E-mail**: `user@madeinweb.com.br`  
- **Senha**: `Test@1234`

Ou abra a classe LoginDataSource para alterar os dados mockados.

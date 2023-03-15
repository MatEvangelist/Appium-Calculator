#language: pt
#encoding: utf-8

@calculadora
Funcionalidade: Somar dois numeros

  @mobile
  Esquema do Cenario: <CT> - Deve somar dois numeros corretamente
    Dado que acesso a calculadora
    Quando clico no numero <PrimeiroNumero>
    E clico novamente no numero <SegundoNumero>
    E clico no botão de soma
    Então valido que a soma dos numeros é igual a <Resultado>

    @CT01
    Exemplos:
      | CT    | PrimeiroNumero | SegundoNumero | Resultado |
      | CT-01 | 5              | 5             | 10        |

    @CT02
    Exemplos:
      | CT    | PrimeiroNumero | SegundoNumero | Resultado |
      | CT-02 | 6              | 6             | 12        |
# 🎮 Cyber-Hero Assembly: Construyendo el Backend de un RPG

Los estudiantes no son solo programadores Java; son **"Ingenieros de la Armería"** en un videojuego futurista. Su trabajo es ensamblar a los héroes, equiparlos con tecnología y gestionar sus misiones.

------

## 🗺️ Roadmap

### Fase 1: El Héroe Solitario (El Problema del Acoplamiento)

**Narrativa:** Tenemos un héroe, "Neo", pero está *hardcodeado* para luchar solo con sus puños. Si queremos darle una espada, tenemos que abrir su código y "operarlo" (modificar la clase `Heroe`).

- **Concepto Teórico:** ¿Qué es una dependencia? Acoplamiento fuerte y sus desventajas.

- **Código "Malo":**

  ```java
  public class Heroe {
      // ❌ MALA PRÁCTICA: El héroe conoce la implementación exacta.
      // Si queremos cambiar a "RayoLaser", tenemos que borrar y reescribir esta línea.
      private EspadaDeMadera arma = new EspadaDeMadera();
  
      public void atacar() {
          arma.golpear(); 
      }
  }
  ```

> Neo está 'soldado' a su espada de madera. No podemos mejorar su equipo sin 'operarlo' (editar su código fuente)".

### Fase 2: El Contrato de la Armería (Interfaces)

**Narrativa:** Para que Neo pueda usar *cualquier* arma (Láser, Plasma, Arco), necesitamos un estándar universal de conexión.

- **Concepto Teórico:** Interfaces y desacoplamiento. Definición de contratos.

- **Actividad:** Crear la interfaz `IArma` con el método `atacar()`.

  ```java
  public interface IArma {
      void atacar();
  }
  ```

  > "Creamos un enchufe universal. Al héroe ya no le importa si es una espada o un láser, solo le importa que tenga el botón 'atacar'".

### Fase 3: Equipando al Héroe (Inyección de Dependencias)

**Narrativa:** Ahora el héroe no crea su arma. La Armería se la entrega antes de salir a la batalla.

- **Concepto Teórico:** Inyección por Constructor. El héroe recibe un `IArma` al nacer.

- **Código "Bueno":**

  ```Java
  import org.springframework.stereotype.Component;
  
  @Component // 1. Marcamos esto como un Bean gestionado por Spring
  public class CanonDePlasma implements IArma {
      @Override
      public String usar() {
          return "¡BOOM! Disparando plasma caliente...";
      }
  }
  public class Heroe {
      private IArma arma;
  
      // Inyección: Alguien más le da el arma
      public Heroe(IArma arma) { 
          this.arma = arma; 
      }
    
    	public void entrarEnCombate() {
          System.out.println("Héroe iniciando combate: " + arma.usar());
      }
  }
  ```

### Fase 4: La Fábrica Automatizada (Spring IoC & Beans)

**Narrativa:** ¡Es tedioso equipar a cada héroe manualmente! Vamos a encender la "IA de la Base" (Spring Framework) para que gestione el inventario y equipe a los héroes automáticamente.

- **Concepto Teórico:** Anotaciones `@Component`, `@Service` y el Contenedor IoC. Spring crea y administra los objetos.
- **Actividad:**
  1. Marcar `CanonDePlasma` con `@Component`.
  2. Marcar `GestorDeMisiones` con `@Service`.
  3. Usar `@Autowired` (o constructor implícito) para que Spring conecte todo.

### Fase 5: El Dilema del Arsenal (Qualifiers)

**Narrativa:** La armería tiene dos armas disponibles: `RifleFrancotirador` y `Escopeta`. Spring está confundido: ¿Cuál le doy al héroe?

- **Concepto Teórico:** Resolución de ambigüedades con `@Qualifier`. Manejo de múltiples implementaciones.
- **Reto:** Usar `@Qualifier("francotirador")` para una misión de sigilo.

**Las Armas:**

```Java
@Component("francotirador") // Le damos un ID específico
public class RifleFrancotirador implements IArma {
    public String usar() { return "Disparo preciso a 1km..."; }
}

@Component("escopeta")
public class EscopetaTactica implements IArma {
    public String usar() { return "¡PUM! Daño en área corto alcance..."; }
}
```

**El Héroe (Decisión):**

```Java
@Component
public class HeroeEspecialista {
    
    private final IArma arma;

    // Usamos @Qualifier para elegir explícitamente el Bean "francotirador"
    public HeroeEspecialista(@Qualifier("francotirador") IArma arma) {
        this.arma = arma;
    }
}
```

> **Explicación:** "Spring encontró dos armas y se confundió. Con `@Qualifier`, le pusimos una etiqueta a la caja del arma para saber cuál entregar".

### Fase 6: Tecnología Alienígena (Configuración con @Bean)

**Narrativa:** Hemos encontrado un "Artefacto Alienígena" (una clase de una librería externa de terceros que no podemos modificar ni ponerle `@Component`). ¿Cómo la integramos al sistema de Spring?

- **Concepto Teórico:** Clases de configuración `@Configuration` y creación manual de `@Bean` para librerías externas.
- **Actividad:** Crear una clase `ConfiguracionAlienigena` para instanciar el artefacto y entregárselo al héroe.

**La Clase Externa (Imagina que viene de una librería .jar):**

```Java
// No podemos escribir @Component aquí porque es código "cerrado" o ajeno
public class ArtefactoAlienigena {
    public String activarPoder() {
        return "🌀 Tecnología desconocida activada...";
    }
}
```

**La Configuración (El Adaptador):**

```Java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class ConfiguracionArea51 {

    @Bean // Creamos el Bean manualmente y lo metemos al contenedor
    public ArtefactoAlienigena tecnologiaX() {
        return new ArtefactoAlienigena();
    }
}
```

> **Explicación:** "Como no podemos ponerle la etiqueta de Spring al artefacto alienígena, construimos un laboratorio especial (`@Configuration`) para prepararlo y entregarlo (`@Bean`)".

---

## 🚀 Ejercicio Final: "La Misión del Boss"

Enunciado:

"El Boss Final se acerca. Debes configurar el sistema para desplegar al Héroe Supremo."

1. Crea una interfaz `SuperPoder`.
2. Implementa dos poderes: `BolaDeFuego` y `RayoHielo` (ambos `@Component`).
3. Crea la clase `HeroeSupremo` que dependa de un `SuperPoder` y de un `Traje` (el traje viene de una configuración externa `@Bean` porque es tecnología experimental).
4. En el `Main`, inyecta el héroe y ejecuta `heroe.luchar()`.
   - *Si usas `BolaDeFuego`, el output debe ser: "¡Quemando al enemigo con traje de Nanofibra!"*

---

**1. Interfaces y POJOs:**

```
// Interfaz
public interface SuperPoder {
    String activar();
}

// Dependencia Externa (Simulada)
public class Traje {
    public String describir() { return "Traje de Nanofibra (Defensa +100)"; }
}
```

**2. Componentes (Beans):**

```Java
@Component
public class BolaDeFuego implements SuperPoder {
    @Override
    public String activar() { return "🔥 ¡Lanzando FUEGO INFERNAL!"; }
}

@Component
public class RayoHielo implements SuperPoder {
    @Override
    public String activar() { return "❄️ ¡Congelando al enemigo!"; }
}
```

**3. Configuración (Beans Manuales):**

```java
@Configuration
public class ConfiguracionEquipo {
    @Bean
    public Traje trajeExperimental() {
        return new Traje();
    }
}
```

**4. El Héroe Supremo (Integración Total):**

```java
@Component
public class HeroeSupremo {
    
    private final SuperPoder poder;
    private final Traje traje;

    // Inyectamos un componente normal Y un bean configurado manualmente
    public HeroeSupremo(@Qualifier("bolaDeFuego") SuperPoder poder, Traje traje) {
        this.poder = poder;
        this.traje = traje;
    }

    public void luchar() {
        System.out.println("--- INICIO DE BATALLA ---");
        System.out.println("Equipamiento: " + traje.describir());
        System.out.println("Ataque: " + poder.activar());
        System.out.println("--- BOSS DERROTADO ---");
    }
}
```

**5. Ejecución (Main):**

```java
@SpringBootApplication
public class RpgBackendApplication implements CommandLineRunner {

    @Autowired
    private HeroeSupremo heroe; // Inyectamos al héroe ya armado

    public static void main(String[] args) {
        SpringApplication.run(RpgBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        heroe.luchar();
    }
}
```

**Salida en Consola:**

```
--- INICIO DE BATALLA ---
Equipamiento: Traje de Nanofibra (Defensa +100)
Ataque: 🔥 ¡Lanzando FUEGO INFERNAL!
--- BOSS DERROTADO ---
```
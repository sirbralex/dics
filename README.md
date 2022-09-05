# dics (DI component storage)
DI component storage with automatic removing and multiple parents support
It consists of two base terms: *DiContex*t and *DiComponent*

**DiContext** - Some scope or lifetime
There are several default declared contexts:
- *Application.diContext*
- *Activity.nonConfigDiContext*
- *Activity.diContext*
- *Fragment.nonConfigDiContext*
- *Fragment.diContext*

![](di_context_hierarchy.png)

**DiComponent** - Marker intarface for any DI component
There are several default other markers:
- *PermanentDiComponent*
- *ApplicationDiComponent*
- *ApplicationPermanentDiComponent*
- *ActivityNonConfigDiComponent*
- *ActivityDiComponent*
- *etc...*

## Some implementations ideas
- DiComponent can depend on several other ones.
- Instance of any DiComponent is stored in some DiContext.
- Instance of DiComponent can be obtained only from DiContext.
- Instance of DiComponent can be stored in one DiContext and obtained from another one.
- Instance of DiComponent is created lazily when it's obtained and is removed when it's not used by any DiContext.
- If DiComponent is PermanentDiComponent, it's not removed while DiContext where it's stored is alive.
- *Activity.diContext* and others like that are extension properties


## Usage
1. Declare *DiComponent* and *DiComponentFactory*

```kotlin
class CoreComponent : ApplicationDiComponent {
    class Factory : DiComponentFactory<CoreComponent> {
        override fun createComponent(provider: DiComponentProvider): CoreComponent {
            return CoreComponent()
        }
    }
}
```
2. Create dependency from one *DiComponent* to another
```kotlin
class AppComponent : ActivityDiComponent {
    class Factory : DiComponentFactory<AppComponent> {
        override fun createComponent(provider: DiComponentProvider): AppComponent {
            val coreComponent = provider.obtainComponent(CoreComponent::class)
            return AppComponent()
        }
    }
}
```

3. Register *DiComponentFactory*
```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        diContext.configure {
            registerComponentFactory(AppComponent.Factory())
            registerComponentFactory(CoreComponent.Factory())
        }
    }
}
```

4. Access *DiComponent* while activity is alive
```kotlin
class SomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = diContext.obtainComponent(AppComponent::class)
    }
}
```

5. Declare *DiComponent* owner if it's not obvious.
```kotlin
class Level1Component : FragmentDiComponent {
    class Factory : DiComponentFactory<Level1Component> {
        override fun createComponent(provider: DiComponentProvider): Level1Component {
            return Level1Component()
        }
    }
}

class Level0Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

// Child of Level0Fragment
class Level1Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        diContext.configure {
            setOwnerOf(Level1Component::class)
        }
        super.onCreate(savedInstanceState)
    }
}

// Child of Level1Fragment
class Level2Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val level1Component = diContext.obtainComponent(Level1Component::class)
    }
}
```

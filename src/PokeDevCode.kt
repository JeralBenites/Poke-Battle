import java.util.*

/**
 * Created by Jeral Benites on 27/08/2017.
 */
enum class Pokemones(val id:Int){ BULBASOR(1),CHARMANDER(2),SQUIRTEL(3),PIKACHU(4)  }

fun main(args: Array<String>){
    println("ELIGE POKEMON")
    for((indice,_p) in Pokemones.values().withIndex()){println("${indice +1}) $_p")}
    val scanner = Scanner(System.`in`)
    val opcion = scanner.nextInt()
    val pokemon : Pokemon =  generarPokemon(opcion)
    mostrarDatos((pokemon),"HAS SELECCIONADO A ")
    val randon = Random()
    val numeroAzar = 1+randon.nextInt(4)
    val pokemonSalvaje:Pokemon = generarPokemon(numeroAzar)
    mostrarDatos((pokemonSalvaje),"TU ENEMIGO ES : ")
    do{
        println("${pokemon._nombre} HP:${pokemon._hp} | ${pokemonSalvaje._nombre} HP: ${pokemonSalvaje._hp}")
        println("ELIGE UN ATAQUE")
        for ((index , a ) in pokemon._ListaAtaques.withIndex()){
            println("${index}: ${a._nombre}")
        }
        val ataqueSeleccionado = scanner.nextInt()
        if(procesarAtaque(pokemon,pokemonSalvaje,ataqueSeleccionado))
        {break;}
        val ataqueAleatorio : Int = 1+randon.nextInt(pokemonSalvaje._ListaAtaques.size)
        if(procesarAtaque(pokemonSalvaje,pokemon,ataqueAleatorio)){
            break
        }else{
            println("LOS DOS POKEMÒNES SIGUNE EN PIE")
            println("..CONTINUAMOS!")
        }
    }while (pokemon._hp > 0 && pokemonSalvaje._hp > 0)
}

fun procesarAtaque(pokemonAtacante: Pokemon, pokemonDefensor: Pokemon, ataqueSeleccionado: Int): Boolean {
    val ataque = pokemonAtacante.ObtenerAtque(ataqueSeleccionado)
    println("${pokemonAtacante._nombre} HA USADO ${ataque._nombre}")
    val valorDanho = calcularDanho(pokemonAtacante._ataque,pokemonAtacante._defensa,ataque)
    println("${pokemonDefensor._nombre} HA RECIBIDO $valorDanho PUNTOS DE DAÑO!")
    pokemonDefensor._hp -= valorDanho
    if(pokemonDefensor._hp <= 0 ){
        println("${pokemonDefensor._nombre} SE AGOTO!")
        println("${pokemonAtacante._nombre} GANÒ LA BATALLA!")
        return true
    }
    return false
}

fun calcularDanho(_ataque: Int, _defensa: Int, ataque: Ataque): Int
        = ((((2 * 1 + 10.0 ) / 250 ) * (_ataque / _defensa)* ataque._poder +2 )*1.5).toInt()

fun mostrarDatos(pokemon: Pokemon , _mensaje: String){
    println("$_mensaje ${pokemon._nombre.toUpperCase()} \nHP:${pokemon._hp} \n" +
            "ATAQUE:${pokemon._ataque} \n" +
            "DEFENSA:${pokemon._defensa}")
}

fun generarPokemon(opcion :Int):Pokemon = when (opcion){
    Pokemones.BULBASOR.id -> Pokemon("Bulbasor" ,45,49,49,arrayOf(Ataque("Vine Whip",45),Ataque("Tackle",40)))
    Pokemones.CHARMANDER.id -> Pokemon("Charmander" ,39,52,43,arrayOf(Ataque("Scratch",45),Ataque("Ember",40)))
    Pokemones.SQUIRTEL.id -> Pokemon("Squirtel" ,44,48,65,arrayOf(Ataque("Tackle",45),Ataque("Watergun",40)))
    Pokemones.PIKACHU.id -> Pokemon("Pikachu" ,35,55,40,arrayOf(Ataque("Thunder shock",45),Ataque("Quick Attack",40)))
    else -> {Pokemon("Missingno",33,136,0,arrayOf(Ataque("Pay Day",45),Ataque("Blind",40))) }
}
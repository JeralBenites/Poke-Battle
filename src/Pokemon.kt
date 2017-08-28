/**
 * Created by Jeral Benites on 27/08/2017.
 */
class Pokemon(val _nombre:String,var _hp:Int,val _ataque : Int , val _defensa :Int , val _ListaAtaques : Array<Ataque>){
    fun ObtenerAtque(orden : Int)= if(orden< _ListaAtaques.size) _ListaAtaques.get(orden)else _ListaAtaques.get(0)
}
class Ataque(val _nombre : String,val _poder:Int)
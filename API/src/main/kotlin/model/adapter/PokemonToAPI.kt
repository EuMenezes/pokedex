package model.adapter

//API https://pokeapi.co/
data class URL(val url: String? = null)
data class NameURL(
    val url: String? = null,
    val name: String? = null
)

//Habilidade
//"Final da URL extrai o id - Ex. JSON: "url": "https://pokeapi.co/api/v2/ability/65/"
data class Abilities(val ability: NameURL? = null)


//Evolução
//"Final da URL extrai o id da eveolução e da update no pokemon - Ex. JSON: "url": "https://pokeapi.co/api/v2/evolution-chain/10/"
data class Species(val evolution_chain: URL? = null) //Chamar JSON da url para pegar a Evolução

data class Chain(
    val species: NameURL? = null, //Pega o Nome e ID(pelo fim da url) do 1º Pokemon
    val evolves_to: List<Chain>? = null //Pega as Segundas evoluções e dentro delas pega as seguinte e assim vai
)

data class EvolutionChain(
    val id: Int? = null,
    val chain: Chain? = null
)

//Imagem
data class OfficialArtwork(val front_default: String? = null)
data class Other(val `official-artwork`: OfficialArtwork? = null)
data class Sprites(val other: Other? = null)

//Tipo
//URL Extrai o Id do tipo
data class Types(val type: NameURL? = null)


//Dados da API
data class PokemonToAPI(
    val id: Int,
    val name : String? = null,
    val abilities: List<Abilities>? = null,
    val species: URL? = null, //Chamar JSON da url para chegar na Evolução
    val sprites: Sprites? = null, //Imagem
    val types: List<Types>? = null
)

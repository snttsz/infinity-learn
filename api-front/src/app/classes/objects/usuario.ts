
export class Usuario 
{
    private id: number = 0;
    private nome: string = ""
    private nomeCompleto: string = "";
    private apelido: string = "";
    private email: string = "";
    private foto: File | null = null;

    constructor (id: number, nome: string, nomeCompleto: string, apelido: string, email: string, foto: File) 
    { 
        this.id = id;
        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.email = email;
        this.foto = foto;
    }

    public getId(): number
    {
        return this.id;
    }

    public getNome(): string
    {
        return this.nome;
    }

    public updateNome(nome: string): void
    {  
        // TODO -> setar no banco de dados
        this.nome = nome;
    }

    public getNomeCompleto(): string
    {
        return this.nomeCompleto;
    }

    public updateNomeCompleto(nomeCompleto: string): void
    {
        // TODO -> setar no banco de dados
        this.nomeCompleto = nomeCompleto;;
    }

    public getApelido(): string
    {
        return this.apelido;
    }

    public updateApelido(apelido: string): void
    {
        // TODO -> setar no banco de dados
        this.apelido = apelido;
    }

    public getEmail(): string
    {
        return this.email;
    }

    public updateEmail(email: string): void
    {
        // TODO -> setar no banco de dados
        this.email = email;
    }

    public getFoto() : File | null
    {
        return this.foto;
    }

    public updateFoto(foto: File): void
    {
        // TODO -> setar no banco de dados
        this.foto = foto;
    }
    
}

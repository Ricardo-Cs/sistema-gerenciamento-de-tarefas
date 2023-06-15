import { Cargo } from "./cargo";

export interface Profissional {
    id: number,
    nome: string,
    username: string,
    cargo: Cargo,
    password: string
}
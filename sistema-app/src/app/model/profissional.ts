import { Cargo } from "./cargo";

export interface Profissional {
    id: number,
    nome: string,
    cargo: Cargo,
    email: string,
    password: string
}
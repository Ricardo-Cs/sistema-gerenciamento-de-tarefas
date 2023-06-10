import { Profissional } from "./profissional";

export interface Tarefas {
    id: number,
    titulo: string,
    due_date: string,
    prioridade: string,
    status: string,
    profissional: Profissional
}
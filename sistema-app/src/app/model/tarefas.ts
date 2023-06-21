import { Profissional } from "./profissional";

export interface Tarefas {
    id: number,
    title: string,
    description: string, 
    due_date: string,
    priority: string,
    status: string,
    profissional: Profissional
}
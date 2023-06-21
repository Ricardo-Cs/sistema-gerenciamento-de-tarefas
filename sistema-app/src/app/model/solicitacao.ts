import { Profissional } from "./profissional";
import { Tarefas } from "./tarefas";

export interface Solicitacao {
    id: number,
    data: string,
    status: string,
    tarefa: Tarefas,
    profissional_solicitado: Profissional
    profissional_solicitante: Profissional
}

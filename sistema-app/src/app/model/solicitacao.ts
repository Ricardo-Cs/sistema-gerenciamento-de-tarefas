import { Profissional } from "./profissional";
import { Tarefas } from "./tarefas";

export interface Solicitacao {
    id: number,
    data: string,
    status: string,
    tarefa: Tarefas,
    profissionalSolicitado: Profissional
    profissionalSolicitante: Profissional
}

import { GET_BACKLOG,
 GET_PROJECT_TASK,
 DELETE_PROJECT_TASK 
} from "../actions/types";
import { DELETE_PROJECT } from "../actions/types";



const initialSate = {
    project_tasks: [],
    project_tasks: {}
}

export default function(state = initialSate, action){
    switch(action.type){

        case GET_BACKLOG:
        return{
            ...state,
            project_tasks: action.payload
        }

        case GET_PROJECT_TASK:
        return{
                ...state,
                project_tasks: action.payload
            }
        case DELETE_PROJECT:
        return{
            ...state
            
            //
        }

        default:
        return state;
    }
}
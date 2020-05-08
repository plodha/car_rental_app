import { proxy } from './proxy';

export class local_proxy implements proxy {
    login(router, api, data) {
        if(typeof(localStorage.getItem("role"))=='undefined'){
            return false;
        };
        console.log(localStorage.getItem("role") );
        if(localStorage.getItem("role") == "Employee") {
            router.navigate(['/admin']);
            return true;
        }
        else if (localStorage.getItem("role") == "Customer"){
            router.navigate(['/customerPage']);
            return true;
        }
        // Will try other proxy
        return false;
    }
}
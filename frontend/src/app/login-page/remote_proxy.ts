import {proxy} from './proxy';
export class remote_proxy implements proxy {
    router: any
    api: any
    data: any
    login(router, api, data) {
        
        api.authAPI(data).subscribe((response:any) => {
            console.log("result")
            console.log(response);
            var status = response.status;
            if(status == "200"){
              var role = response.role;
              console.log(role)
      
      
              if(role == 'Employee') {
                 router.navigate(['/admin']);
              }
              else{
                 router.navigate(['/customerPage']);
              }
              //ole: "Customer", id: "4", status: "200", username: "jeyasri_s"
              localStorage.setItem("username", response.username);
              localStorage.setItem("id",response.id );
              localStorage.setItem('role',response.role)
              api.getCustomerById(localStorage.id).subscribe((cust:any) => {
                console.log(cust)
                localStorage.setItem('customerId',cust.id)
              });
      
      
            }
            else {
            alert('Invalid credentials')
            }
      
          })
    }
}
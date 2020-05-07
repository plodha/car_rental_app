import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import {ApiService} from '../api.service'

@Component({
  selector: 'app-cancel-page',
  templateUrl: './cancel-page.component.html',
  styleUrls: ['./cancel-page.component.scss']
})
export class CancelPageComponent implements OnInit {
  isLoadingResults = false;
  public endDate = "";
  constructor(private router: Router, private formBuilder: FormBuilder, private api: ApiService) { }

  ngOnInit(): void {
    var OBJ = this;
    this.api.getCustomerById(localStorage.id).subscribe((cust:any) => {
      console.log(cust)
      console.log(cust.membershipEndDate)
      var memEndDate = cust.membershipEndDate
      var splitted = memEndDate.split("-", 3);
      OBJ.endDate = splitted[1]+ "/"+splitted[2]+"/"+splitted[0]
    });
    console.log(localStorage.username != undefined)
  if(localStorage.username != undefined ) {
    document.getElementById('profileName').innerHTML += localStorage.username
  }
  else {
    this.router.navigate(['/login']);

  }


  }
  cancel(operation):void {

    if(operation == 'Cancel') {
      var formData = {}
      formData['userId'] = localStorage.id;
      this.api.cancelMembershipAPI(formData).subscribe((res:any) => {
        console.log(res);

          //this.router.navigate(['/vehicle']);
          alert('Your membership has been cancelled')
          this.router.navigate(['/login'])

        });
    }

  }

  closeWindow():void {
  this.router.navigate(['/customerPage'])
  }

}

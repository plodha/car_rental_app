import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import {ApiService} from '../api.service'
import {local_proxy} from './local_proxy';
import {remote_proxy} from './remote_proxy';


/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  loginForm: FormGroup;
  username = '';
  password = '';
  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();
  constructor(private router: Router, private formBuilder: FormBuilder,private api: ApiService) {

  }

  ngOnInit(): void {
    let lp = new local_proxy();
    
    lp.login(this.router, this.api, null);
    this.loginForm = this.formBuilder.group({
      username : [null, Validators.required],
      password : [null, Validators.required]
    });
  }
  onFormSubmit() {
    this.isLoadingResults = true;
    //  this.router.navigate(['/home']);


    let username = (this.loginForm.get('username').value);
    let password = (this.loginForm.get('password').value);


    var data = {}
    data['username'] = username
    data['password'] = password
    console.log(data)

    
    let rp = new remote_proxy();
    
    rp.login(this.router, this.api, data); 

  
  }

}

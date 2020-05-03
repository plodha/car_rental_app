import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

@Component({
  selector: 'app-cancel-page',
  templateUrl: './cancel-page.component.html',
  styleUrls: ['./cancel-page.component.scss']
})
export class CancelPageComponent implements OnInit {
  isLoadingResults = false;
  constructor(private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }
  cancel():void {
    this.router.navigate(['/login']);
  }

}

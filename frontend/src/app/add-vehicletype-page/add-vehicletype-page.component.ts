import { Component, OnInit } from '@angular/core';
//import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';

import {ActivatedRoute, Router } from '@angular/router';
import {ApiService} from '../api.service'

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}


@Component({
  selector: 'app-add-vehicletype-page',
  templateUrl: './add-vehicletype-page.component.html',
  styleUrls: ['./add-vehicletype-page.component.scss']
})
export class AddVehicletypePageComponent implements OnInit {
  addVehicleTypeForm:FormGroup;

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();
  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,
      private api: ApiService) { }

  ngOnInit(): void {

        this.addVehicleTypeForm = this.formBuilder.group({

          vehicleClass:[null,[Validators.required, Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
          vehicleSize:[null,[Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(2)]]
        });
      }
      onFormSubmit() {
        this.isLoadingResults = true;
        //this.router.navigate(['/login']);

        var formData = {}

        formData['vehicleClass'] = this.addVehicleTypeForm.get('vehicleClass').value
        formData['vehicleSize'] = parseInt(this.addVehicleTypeForm.get('vehicleSize').value)



        this.api.createVehicleType(formData).subscribe((res:any)=>{
          this.router.navigate(['/vehicletypes']);
        });

      }
}

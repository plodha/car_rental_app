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


interface VehicleType {
  value: string;
  id: string;
}

@Component({
  selector: 'app-add-price-page',
  templateUrl: './add-price-page.component.html',
  styleUrls: ['./add-price-page.component.scss']
})
export class AddPricePageComponent implements OnInit {

  vehicleTypes: VehicleType[] = [];
  addPriceForm:FormGroup;
  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();

  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,
      private api: ApiService) {
        this.actr.data.subscribe((data)=>{
          var vehicleTypes = data.vehicleType;
          console.log(vehicleTypes)
          var i = 0;
          for (i = 0; i < vehicleTypes.length; i++) {

            var obj = vehicleTypes[i];

            var vehicleType = {
              id: obj.id,
              value: obj.vehicleClass
            }
            this.vehicleTypes[i] = vehicleType;
          }

        });

       }

  ngOnInit(): void {

    this.addPriceForm = this.formBuilder.group({

      vehicleType:[null,Validators.required],
      lateFee:[null,[Validators.required, Validators.pattern("^[0-9]+(\\.[0-9]{2})?$"),Validators.maxLength(5)]],
      hourlyPrice:[null,[Validators.required, Validators.pattern("^[0-9]+(\\.[0-9]{2})?$"),Validators.maxLength(5)]],
      hourlyRange:[null,[Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(5)]]
    });
  }

  onFormSubmit() {
    this.isLoadingResults = true;
    //this.router.navigate(['/login']);

    var formData = {}

    formData['hourlyRange'] = this.addPriceForm.get('hourlyRange').value
    formData['hourlyPrice'] = parseFloat(this.addPriceForm.get('hourlyPrice').value)
    formData['lateFee'] = parseFloat(this.addPriceForm.get('lateFee').value)
    var vehicleType = {}
    vehicleType['id'] = this.addPriceForm.get('vehicleType').value
    formData['vehicleTypeId'] = vehicleType


    this.api.addPriceAPI(formData).subscribe((res:any)=>{
      this.router.navigate(['/priceList']);
    });

  }

}

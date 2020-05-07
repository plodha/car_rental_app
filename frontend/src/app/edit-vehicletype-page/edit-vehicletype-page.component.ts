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
  selector: 'app-edit-vehicletype-page',
  templateUrl: './edit-vehicletype-page.component.html',
  styleUrls: ['./edit-vehicletype-page.component.scss']
})
export class EditVehicletypePageComponent implements OnInit {

  editVehicleTypeForm:FormGroup;

  isLoadingResults = false;
  matcher = new MyErrorStateMatcher();
  constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,
      private api: ApiService) { }

  ngOnInit(): void {
    var vehicleTypeId = this.actr.snapshot.params.id;
    //  this.getVehicleTypeById(this.actr.snapshot.params.id);
          this.editVehicleTypeForm = this.formBuilder.group({
            id:[null,Validators.required],
            vehicleClass:[null,[Validators.required, Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
            vehicleSize:[null,[Validators.required, Validators.pattern("^[0-9]+$"),Validators.maxLength(2)]]
          });
    this.actr.data.subscribe((data)=>{
      var vehicleTypes = data.vehicleType
      var i = 0;
      for (i = 0; i < vehicleTypes.length; i++) {

        var obj = vehicleTypes[i]
        if(obj.id == vehicleTypeId){
          this.editVehicleTypeForm.setValue({
            id : obj.id,
            vehicleClass : obj.vehicleClass,
            vehicleSize : obj.vehicleSize,

          });
        }

      }
    });




      }
      onFormSubmit() {
        this.isLoadingResults = true;
        //this.router.navigate(['/login']);

        var formData = {}
        formData['id'] = this.editVehicleTypeForm.get('id').value;

        formData['vehicleClass'] = this.editVehicleTypeForm.get('vehicleClass').value
        formData['vehicleSize'] = parseInt(this.editVehicleTypeForm.get('vehicleSize').value)



        this.api.updateVehicleTypeAPI(formData).subscribe((res:any)=>{
          this.router.navigate(['/vehicletypes']);
        });

      }

      getVehicleTypeById(id: any) {
        var object = this;
       this.api.getVehicleTypeById(id).subscribe((data: any) => {

         console.log(data)
         this.editVehicleTypeForm.setValue({
           id : data.id,
           vehicleClass : data.vehicleClass,
           vehicleSize : data.vehicleSize,




         });
       });
     }

}

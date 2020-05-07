import { Component, OnInit,ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

import {ActivatedRoute, Router } from '@angular/router';


import {ApiService} from '../api.service'


export interface VehicleType {
  id:number;
  vehicleClass: string;
  vehicleSize: number;


}
@Component({
  selector: 'app-vehicle-type-page',
  templateUrl: './vehicle-type-page.component.html',
  styleUrls: ['./vehicle-type-page.component.scss']
})
export class VehicleTypePageComponent implements OnInit {

    displayedColumns: string[] = ['id', 'vehicleClass', 'vehicleSize','star'];
    dataSource: MatTableDataSource<VehicleType>;
    ELEMENT_DATA: VehicleType[] = [];
    isLoadingResults = false;
    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
    @ViewChild(MatSort, {static: true}) sort: MatSort;


    constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {
      this.actr.data.subscribe((data)=>{
        var vehicleTypes = data.vehicleType
        var i = 0;
        for (i = 0; i < vehicleTypes.length; i++) {

          var obj = vehicleTypes[i]
          var vehicleType = {
            id: obj.id,
            vehicleClass:obj.vehicleClass,
            vehicleSize: obj.vehicleSize
          }
          this.ELEMENT_DATA[i] = vehicleType;
        }
      });
        this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
   }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    console.log(localStorage.username != undefined)
    if(localStorage.username != undefined ) {
      document.getElementById('profileName').innerHTML += localStorage.username
    }
    else {
      this.router.navigate(['/login']);

    }
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  updateVehicleType(operation, ele){
    console.log('update ');
    console.log(ele)
    if(operation == 'Update') {
      this.router.navigate(['/editVehicleType/'+ele.id],{state:{data:ele}});
    }
    if(operation == 'Delete') {
      var formData = {}
      formData['vehicleTypeId'] = ele.id;
      console.log(ele.id)

      this.isLoadingResults = false;
      this.api.deleteVehicleType(formData).subscribe((res:any) => {
          console.log(res);
          this.isLoadingResults = false;

          //this.router.navigate(['/vehicle']);

          location.reload();


        });

        location.reload();
      //this.router.navigate(['/editVehicle/'+ele.id]);
    }


  }
}

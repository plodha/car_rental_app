import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {ActivatedRoute, Router } from '@angular/router';
import {ApiService} from '../api.service'


export interface Vehicle {
  id:number;
  VehicleType: string;
  model: string;
  make: string;
  year: string;
  vin:string;
  licensePlate:string;
  location:string;
  vehicleCondition:string;
}


@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.scss']
})
export class VehiclePageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'VehicleType', 'model','make','year','licensePlate', 'vin','location','vehicleCondition','star'];
  dataSource: MatTableDataSource<Vehicle>;
  ELEMENT_DATA: Vehicle[] = [];
 @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
 @ViewChild(MatSort, {static: true}) sort: MatSort;


 constructor(private router: Router,private actr: ActivatedRoute, private formBuilder: FormBuilder,private api: ApiService) {
   // Create 100 users
   // Assign the data to the data source for the table to render
  this.actr.data.subscribe((data)=>{
    console.log(data);
    var vehicles = data.vehicle;
    console.log(vehicles)
    console.log(vehicles.length)

    var i = 0;
    for (i = 0; i < vehicles.length; i++) {

      var obj = vehicles[i];
      console.log(obj)
      var vehicle = {
        id: obj.id,
        VehicleType:obj.vehicleTypeId.vehicleClass,
        model: obj.model,
         make:obj.make,
         year:obj.year,
         vin:obj.vin,
         licensePlate:obj.licensePlate,
         location:obj.location.name,
         vehicleCondition:obj.vehicleCondition
      }
      this.ELEMENT_DATA[i] = vehicle;
    }
  });

   this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);

 }

 ngOnInit() {
   this.dataSource.paginator = this.paginator;
   this.dataSource.sort = this.sort;
 }

 applyFilter(event: Event) {
   const filterValue = (event.target as HTMLInputElement).value;
   this.dataSource.filter = filterValue.trim().toLowerCase();

   if (this.dataSource.paginator) {
     this.dataSource.paginator.firstPage();
   }
 }



}

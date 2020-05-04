import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditVehiclePageComponent } from './edit-vehicle-page.component';

describe('EditVehiclePageComponent', () => {
  let component: EditVehiclePageComponent;
  let fixture: ComponentFixture<EditVehiclePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditVehiclePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditVehiclePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

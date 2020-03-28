import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclePageComponent } from './vehicle-page.component';

describe('VehiclePageComponent', () => {
  let component: VehiclePageComponent;
  let fixture: ComponentFixture<VehiclePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiclePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

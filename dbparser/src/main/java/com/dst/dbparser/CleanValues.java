package com.dst.dbparser;

import java.util.HashMap;

public class CleanValues {

//    public static final HashMap<String, Integer> cleanDataArrayIndex = new HashMap<>();

    public static final String tempHydOil = "HydOilTemp";
    public static final String tempCoolant = "CoolantTemp";
    public static final String tempGearbox = "GearboxTemp";
    public static final String tempTurbo = "TurboTemp";
    public static final String tempEnv = "EnvTemp";
    public static final String pressA = "PressA";
    public static final String pressB = "PressB";
    public static final String pressAttach = "PressAttach";
    public static final String pressFanDrive = "PressFanDrive";

    public static final String engineSpeed = "EngineSpeed";
    public static final String engSpeedTsc1 = "ReqEngSpeedPedal";
    public static final String maxJoy = "ReqEngSpeedJoy";
    public static final String accelPedal = "AccelPedal";

    public static final String joyY = "Joy F(+)/B(-)";
    public static final String joyX = "Joy R(+)/L(-)";
    public static final String fuelLevel = "Fuel";

    public static final String actualVelocity = "ActualVelocity";
    public static final String velLimit = "VelocityLimit";
    public static final String inchPedal = "InchPedal";

    // Угол наклона ковша, сдвиг -135 град
    public static final String bucketTilt = "BucketTiltAngle";
    // Высота положения стрелы, сдвиг -40 (0,1 м)
    public static final String jibTilt = "JibHeight";
    // Угол тангажа рамы, сдвиг -90
    public static final String chassisPitch = "ChassisPitchAngle";
    // Угол крена рамы, сдвиг -90
    public static final String chassisTilt = "ChassisTiltAngle";

    public static final String driveMode = "DriveMode";
    public static final String velocityLimiter = "VelocityLimiter";
    public static final String swtGear1 = "SwtGear1";
    public static final String swtGear2 = "SwtGear2";
    public static final String sigGear1 = "SigGear1";
    public static final String sigGear2 = "SigGear2";

    public static final String errQuan = "ErrorsQuantity";
    public static final String isHydSysBlock = "HydSysBlocked";
    public static final String isSwimMode = "SwimMode";
    public static final String isStartPossible = "StartPossible";
    public static final String isControlForb = "ControlForbid";
    public static final String isContForbSpeed = "ContForbidBySpeed";
    public static final String isContForbOpAbsent = "ContForbidOpAbsent";
    public static final String isContForbUnintentionall = "UnintentionallControlForbid";
    public static final String isMoveBackward = "MoveBackwardSig";
    public static final String isEmerDir = "EmergencyDirChoice";
    public static final String isOperMode = "CustomOperatorMode";
    public static final String isEcoMode = "EcoMode";
    public static final String swtParkingSens = "ParkingSwtSens";
    public static final String swtOperAbsentSens = "OperatorAbsent";
    public static final String swtParkingButton = "ParkingButton";
    public static final String swtAirFilter = "AirFilterClogged";
    public static final String swtHydFiltCloggVac = "VacHydFiltClogg";
    public static final String swtHydFiltCloggPress = "PressHydFiltClogg";
    public static final String swtHydFiltCloggSuction = "SuctionHydFiltClogg";

    public static final String hstErr1 = "HstErr1";
    public static final String hstErr2 = "HstErr2";
    public static final String hstErr3 = "HstErr3";
    public static final String hstErr4 = "HstErr4";
    public static final String hstErr5 = "HstErr5";
    public static final String hstErr6 = "HstErr6";

    public static final String spn1p1 = "spn1p1";
    public static final String spn1p2 = "spn1p2";
    public static final String spn1p3 = "spn1p3";
    public static final String fmi1 = "fmi1";

    public static final String spn2p1 = "spn2p1";
    public static final String spn2p2 = "spn2p2";
    public static final String spn2p3 = "spn2p3";
    public static final String fmi2 = "fmi2";

    public static final String spn3p1 = "spn3p1";
    public static final String spn3p2 = "spn3p2";
    public static final String spn3p3 = "spn3p3";
    public static final String fmi3 = "fmi3";

    public static final String spn4p1 = "spn4p1";
    public static final String spn4p2 = "spn4p2";
    public static final String spn4p3 = "spn4p3";
    public static final String fmi4 = "fmi4";

    public static final String spn5p1 = "spn5p1";
    public static final String spn5p2 = "spn5p2";
    public static final String spn5p3 = "spn5p3";
    public static final String fmi5 = "fmi5";

    public static final String spn6p1 = "spn6p1";
    public static final String spn6p2 = "spn6p2";
    public static final String spn6p3 = "spn6p3";
    public static final String fmi6 = "fmi6";

    public static final String permMotSpeed = "PermMotRPM";
    public static final String tempMotSpeed = "TempMotRPM";
    // для обработчиков
    public static final String motoHours = "hours";
    public static final String motoMinutes = "minutes";
    // после склейки
    public static final String workHours = "WorkHours";

    // Ограничения крутящего момента (контроллер НО), 0..100 %
    public static final String torqueLimit = "TorqueLimit";
    // Ограничение скорости  (контроллер НО), 0..100 %
    public static final String velocLimitPct = "VelLimitPct";
    // Виртуальная ручка газа (контроллер НО), 0..100 %
    public static final String virtualAccel = "VirtualAccel";
    // Реле рул упр авар  0 - разомк; 1 - замк
    public static final String emerSteerRelay = "EmSteerRel";
    // Реле рул упр осн 0 - разомк; 1 - замк
    public static final String steerRelay = "SteerRel";
    // Круиз-контроль МИНУС
    public static final String cruiseContMinus = "CC-Min";
    // Круиз-контроль ПЛЮС
    public static final String cruiseContPlus = "CC-Plus";
    // Круиз-контроль ВКЛ
    public static final String cruiseContOn = "CC-Swt";
    // Направление движения
    public static final String driveDirection = "DriveDir";

    // Ошибка - Нет связи с двигателем
    public static final String engCpuConLost = "EngDisconnected";
    // Ошибка - Нет связи с контроллером ГСТ
    public static final String hstCpuConLost = "HstDisconnected";

    // Кн БСУ
    public static final String btnBSU = "btnBSU";
    // Кн Движение по дорогам
    public static final String btnRoad = "btnRoad";
    // Кн Диапазон скорости A1-3
    public static final String btnA1_3 = "btnA1_3";
    // Кн Диапазон скорости F1
    public static final String btnF1 = "btnF1";
    // Кн Подогрев топлива
    public static final String btnFuelHeat = "btnFuelHeat";
    // Кн Демпфирование ходовых колебаний
    public static final String btnDumping = "btnDumping";
    // Кн Плавающее положение
    public static final String btnSwim = "btnSwim";
    // Кн Движение Назад
    public static final String btnBack = "btnBack";
    // Кн Движение Вперед
    public static final String btnFront = "btnFront";

    // СУ Стоп-сигналы
    public static final String sigStop = "SigStop";
    // СУ Светопроблесковый маяк
    public static final String sigBeacon = "SigBeacon";
    // СУ Аварийное рулевое управление
    public static final String sigEmerSteer = "SigEmerSteer";
    // СУ Стояночный тормоз, CCO
    public static final String sigParking = "SigParking";
    // СУ Ride Control
    public static final String sigRideCont = "SigRideCont";
    // СУ подогрев топлива
    public static final String sigFuelHeat = "SigFuelHeat";
    // СУ Блокировка РО
    public static final String sigAttBlock = "SigAttBlock";
    // СУ Обогрев заднего стекла
    public static final String sigRearWindHeat = "SigRearWindHeat";
    // СУ Запуск ДВС
    public static final String sigStartEng = "SigStartEng";
    // СУ Клапан Реверса Fan_Drive
    public static final String sigRevFan = "SigRevFan";
    // СУ Свет. сигнал з/х
    public static final String sigRevBlink = "SigRevBlink";
    // СУ Звук. Сигнал з/х
    public static final String sigRevSound = "SigRevSound";
}